package com.example.crawlssi.service.impl;

import com.example.crawlssi.outgate.ssi.SsiClient;
import com.example.crawlssi.outgate.ssi.request.CompanyFinanceReqModel;
import com.example.crawlssi.outgate.ssi.response.CompanyFinanceModel;
import com.example.crawlssi.outgate.ssi.response.CompanyFinanceResp;
import com.example.crawlssi.outgate.ssi.response.SingleStockModel;
import com.example.crawlssi.outgate.ssigraph.SsiGraphClient;
import com.example.crawlssi.outgate.ssigraph.request.DailyStockReqModel;
import com.example.crawlssi.outgate.ssigraph.response.DailyStockPriceModel;
import com.example.crawlssi.outgate.ssigraph.response.DailyStockResp;
import com.example.crawlssi.repository.DailyStockPriceRepository;
import com.example.crawlssi.repository.FinanceIndicatorRepository;
import com.example.crawlssi.repository.entity.DailyStockPrice;
import com.example.crawlssi.repository.entity.FinanceIndicator;
import com.example.crawlssi.service.SubCrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class SubCrawlSsiServiceImpl implements SubCrawlSsiService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final SsiClient ssiClient;
    private final FinanceIndicatorRepository financeIndicatorRepository;
    private final SsiGraphClient ssiGraphClient;
    private final DailyStockPriceRepository dailyStockPriceRepository;

    @Autowired
    public SubCrawlSsiServiceImpl(
            SsiClient ssiClient,
            FinanceIndicatorRepository financeIndicatorRepository,
            SsiGraphClient ssiGraphClient,
            DailyStockPriceRepository dailyStockPriceRepository
    ) {
        this.financeIndicatorRepository = financeIndicatorRepository;
        this.ssiClient = ssiClient;
        this.ssiGraphClient = ssiGraphClient;
        this.dailyStockPriceRepository = dailyStockPriceRepository;
    }

    private void saveCompanyFinanceIndicatorToDb(String symbol, CompanyFinanceModel companyFinanceModel) {
        FinanceIndicator financeIndicator = new FinanceIndicator();
        financeIndicator.setSymbol(symbol);
        BeanUtils.copyProperties(companyFinanceModel, financeIndicator);

        try {
            financeIndicatorRepository.save(financeIndicator);
        } catch (Exception e) {
            log.info("Failed when save financeIndicator to Db --> FinanceI: {} - {} - {}", financeIndicator, e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveFinanceIndicator(String symbol) {
        CompanyFinanceReqModel companyFinanceReqModel = new CompanyFinanceReqModel();

        companyFinanceReqModel.setSymbol(symbol);
        companyFinanceReqModel.setSize(100000);

        try {
            CompanyFinanceResp companyFinanceResp = ssiClient.getCompanyFinance(companyFinanceReqModel);
            companyFinanceResp.getData().getFinancialIndicator().getDataList().forEach(
                    item -> saveCompanyFinanceIndicatorToDb(symbol, item)
            );
        } catch (Exception e) {
            log.info("Failed when crawl CompanyFinance --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void crawlCompanyFinance() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveFinanceIndicator(item.getCode()));
        });
    }

    private void saveDailyStockToDb(DailyStockPriceModel dailyStockPriceModel) {
        DailyStockPrice dailyStockPrice = new DailyStockPrice();
        BeanUtils.copyProperties(dailyStockPriceModel, dailyStockPrice);
        try {
            dailyStockPriceRepository.save(dailyStockPrice);
        } catch ( Exception e) {
            log.info("Failed to save dailyStockPrice to DB --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveFromSsiGraph(String symbol) {
        DailyStockReqModel dailyStockReqModel = new DailyStockReqModel();

        dailyStockReqModel.setSymbol(symbol);
        dailyStockReqModel.setSize(1000000000);
        dailyStockReqModel.setFromDate("13/03/2021");
        dailyStockReqModel.setOffset(1);
        dailyStockReqModel.setToDate(simpleDateFormat.format(new Date(System.currentTimeMillis())));

        try {
            DailyStockResp dailyStockResp = ssiGraphClient.getDailyStockData(dailyStockReqModel);
            dailyStockResp.getData().getStockPrice().getDataList().forEach(this::saveDailyStockToDb);
        } catch (Exception e) {
            log.info("Error to crawl daily stock --> {} - {}", e.getMessage(), e.getCause());
        }

    }

    @Override
    public void crawlStockDailyFromSsiGraph() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveFromSsiGraph(item.getCode()));
        });
    }
}
