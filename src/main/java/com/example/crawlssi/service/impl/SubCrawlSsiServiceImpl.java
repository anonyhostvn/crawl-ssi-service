package com.example.crawlssi.service.impl;

import com.example.crawlssi.outgate.ssi.SsiClient;
import com.example.crawlssi.outgate.ssi.request.CompanyFinanceReqModel;
import com.example.crawlssi.outgate.ssi.request.CorporateActionReqModel;
import com.example.crawlssi.outgate.ssi.request.ShareHolderReqModel;
import com.example.crawlssi.outgate.ssi.response.*;
import com.example.crawlssi.outgate.ssigraph.SsiGraphClient;
import com.example.crawlssi.outgate.ssigraph.request.DailyStockReqModel;
import com.example.crawlssi.outgate.ssigraph.response.DailyStockPriceModel;
import com.example.crawlssi.outgate.ssigraph.response.DailyStockResp;
import com.example.crawlssi.repository.CorporateActionRepository;
import com.example.crawlssi.repository.DailyStockPriceRepository;
import com.example.crawlssi.repository.FinanceIndicatorRepository;
import com.example.crawlssi.repository.ShareHolderRepository;
import com.example.crawlssi.repository.entity.CorporateAction;
import com.example.crawlssi.repository.entity.DailyStockPrice;
import com.example.crawlssi.repository.entity.FinanceIndicator;
import com.example.crawlssi.repository.entity.ShareHolder;
import com.example.crawlssi.service.SubCrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final ShareHolderRepository shareHolderRepository;
    private final CorporateActionRepository corporateActionRepository;

    private final List<String> dateTypeList;
    private final List<String> eventCodeList;

    @Autowired
    public SubCrawlSsiServiceImpl(
            SsiClient ssiClient,
            FinanceIndicatorRepository financeIndicatorRepository,
            SsiGraphClient ssiGraphClient,
            DailyStockPriceRepository dailyStockPriceRepository,
            ShareHolderRepository shareHolderRepository,
            CorporateActionRepository corporateActionRepository
    ) {
        this.financeIndicatorRepository = financeIndicatorRepository;
        this.ssiClient = ssiClient;
        this.ssiGraphClient = ssiGraphClient;
        this.dailyStockPriceRepository = dailyStockPriceRepository;
        this.shareHolderRepository = shareHolderRepository;
        this.corporateActionRepository = corporateActionRepository;

        this.dateTypeList = Arrays.asList("ex_date", "pub_date");
        this.eventCodeList = Arrays.asList("dhcd", "ny", "gdnb", "tct", "kqkd", "skk");
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

    private void saveDailyStockToDb(String symbol, DailyStockPriceModel dailyStockPriceModel) {
        DailyStockPrice dailyStockPrice = new DailyStockPrice();
        BeanUtils.copyProperties(dailyStockPriceModel, dailyStockPrice);
        dailyStockPrice.setSymbol(symbol);
        try {
            dailyStockPriceRepository.save(dailyStockPrice);
            log.info("Save a price of {} in {}", symbol, dailyStockPrice.getTradingDate());
        } catch (Exception e) {
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
            dailyStockResp.getData().getStockPrice().getDataList().forEach(item -> saveDailyStockToDb(symbol, item));
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

    private void saveShareHolderToDb(String symbol, ShareHolderModel shareHolderModel) {
        ShareHolder shareHolder = new ShareHolder();
        BeanUtils.copyProperties(shareHolderModel, shareHolder);
        shareHolder.setSymbol(symbol);

        try {
            shareHolderRepository.save(shareHolder);
        } catch (Exception e) {
            log.info("Failed to save shareholder to db {} , {} - {}", shareHolder, e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveShareHolder(String symbol) {
        ShareHolderReqModel shareHolderReqModel = new ShareHolderReqModel();
        shareHolderReqModel.setSymbol(symbol);
        shareHolderReqModel.setSize(1000000);
        shareHolderReqModel.setOffset(1);

        try {
            ShareHolderResp shareHolderResp = ssiClient.getShareHolder(shareHolderReqModel);
            shareHolderResp.getData().getShareHolders().getDataList().forEach(item -> saveShareHolderToDb(symbol, item));
        } catch (Exception e) {
            log.info("Failed to crawl shareholder --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void crawlShareHolder() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveShareHolder(item.getCode()));
        });
    }

    private void saveCorporateActionToDb(CorporateActionModel corporateActionModel) {
        CorporateAction corporateAction = new CorporateAction();
        BeanUtils.copyProperties(corporateActionModel, corporateAction);
//        if (corporateAction.getEventDescription().length() > 900)
//            corporateAction.setEventDescription(corporateAction.getEventDescription().substring(0,800));

        try {
            corporateActionRepository.save(corporateAction);
        } catch (Exception e) {
            log.info("Failed to save corporateActionModel to Db {} --> {} - {}", corporateAction, e.getMessage(), e.getCause());
        }
    }


    private void crawlAndSaveCorporateAction(String symbol) {
        CorporateActionReqModel corporateActionReqModel = new CorporateActionReqModel();
        corporateActionReqModel.setSymbol(symbol);
        corporateActionReqModel.setOffset(1);
        corporateActionReqModel.setSize(1000000000);
        corporateActionReqModel.setFromDate("01/01/1970");
        corporateActionReqModel.setToDate(simpleDateFormat.format(new Date(System.currentTimeMillis())));

        for (String dateType : dateTypeList)
            for (String eventCode : eventCodeList) {
                corporateActionReqModel.setEventCode(eventCode);
                corporateActionReqModel.setDateType(dateType);
                try {
                    CorporateActionResp corporateActionResp = ssiClient.getCorporateAction(corporateActionReqModel);
                    corporateActionResp.getData().getCorporateActions().getDataList().forEach(this::saveCorporateActionToDb);
                } catch (Exception e) {
                    log.info("Failed to crawl corporate action --> {} - {}", e.getMessage(), e.getCause());
                }
            }

    }

    @Override
    public void crawlCorporateAction() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveCorporateAction(item.getCode()));
        });
    }
}
