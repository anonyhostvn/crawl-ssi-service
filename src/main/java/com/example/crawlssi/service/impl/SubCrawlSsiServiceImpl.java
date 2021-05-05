package com.example.crawlssi.service.impl;

import com.example.crawlssi.outgate.ssi.SsiClient;
import com.example.crawlssi.outgate.ssi.request.CompanyFinanceReqModel;
import com.example.crawlssi.outgate.ssi.response.CompanyFinanceModel;
import com.example.crawlssi.outgate.ssi.response.CompanyFinanceResp;
import com.example.crawlssi.outgate.ssi.response.SingleStockModel;
import com.example.crawlssi.repository.CompanyProfileRepository;
import com.example.crawlssi.repository.FinanceIndicatorRepository;
import com.example.crawlssi.repository.NewsRepository;
import com.example.crawlssi.repository.TradingExchangeRepository;
import com.example.crawlssi.repository.entity.FinanceIndicator;
import com.example.crawlssi.service.SubCrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class SubCrawlSsiServiceImpl implements SubCrawlSsiService {

    private final SsiClient ssiClient;
    private final FinanceIndicatorRepository financeIndicatorRepository;

    @Autowired
    public SubCrawlSsiServiceImpl(
            SsiClient ssiClient,
            FinanceIndicatorRepository financeIndicatorRepository
    ) {
        this.financeIndicatorRepository = financeIndicatorRepository;
        this.ssiClient = ssiClient;
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
}
