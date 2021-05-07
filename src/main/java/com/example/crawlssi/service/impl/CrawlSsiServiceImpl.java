package com.example.crawlssi.service.impl;

import com.example.crawlssi.exceptions.CustomBusinessLogicException;
import com.example.crawlssi.factory.resfact.ResponseStatusEnum;
import com.example.crawlssi.outgate.ssi.SsiClient;
import com.example.crawlssi.outgate.ssi.request.CapAndDividendReqModel;
import com.example.crawlssi.outgate.ssi.request.CompanyProfileReqModel;
import com.example.crawlssi.outgate.ssi.request.NewsReqModel;
import com.example.crawlssi.outgate.ssi.response.*;
import com.example.crawlssi.repository.*;
import com.example.crawlssi.repository.entity.*;
import com.example.crawlssi.service.CrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CrawlSsiServiceImpl implements CrawlSsiService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final SsiClient ssiClient;
    private final TradingExchangeRepository tradingExchangeRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final NewsRepository newsRepository;
    private final CashDividendRepository cashDividendRepository;
    private final AssetRepository assetRepository;
    private final CapitalRepository capitalRepository;


    @Autowired
    public CrawlSsiServiceImpl(
            SsiClient ssiClient,
            TradingExchangeRepository tradingExchangeRepository,
            CompanyProfileRepository companyProfileRepository,
            NewsRepository newsRepository,
            CashDividendRepository cashDividendRepository,
            AssetRepository assetRepository,
            CapitalRepository capitalRepository
    ) {
        this.tradingExchangeRepository = tradingExchangeRepository;
        this.companyProfileRepository = companyProfileRepository;
        this.ssiClient = ssiClient;
        this.newsRepository = newsRepository;
        this.cashDividendRepository = cashDividendRepository;
        this.assetRepository = assetRepository;
        this.capitalRepository = capitalRepository;
    }

    private boolean isTradingExExist(String exchangeCode) {
        List<TradingExchange> tradingExchangeList = tradingExchangeRepository
                .findByExchangeCode(exchangeCode)
                .orElseThrow(() -> new CustomBusinessLogicException(ResponseStatusEnum.UNKNOWN_ERROR));

        return !tradingExchangeList.isEmpty();
    }

    private void saveSingExToDbFromSingleStock(SingleStockModel singleStockModel) {
        if (singleStockModel == null) return;
        if (isTradingExExist(singleStockModel.getExchange())) return;

        TradingExchange tradingExchange = new TradingExchange();
        tradingExchange.setExchangeCode(singleStockModel.getExchange());
        tradingExchange.setExchangeName(singleStockModel.getExchange());
        try {
            tradingExchangeRepository.save(tradingExchange);
        } catch (Exception e) {
            log.info("Failed to save trading exchange : {} to DB", tradingExchange.getExchangeCode());
        }
    }

    @Override
    public boolean crawlAllStock() {
        try {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            if (listSingleStock == null) return false;
            listSingleStock.forEach(this::saveSingExToDbFromSingleStock);
        } catch (Exception e) {
            log.info("Failed to handle crawlAllStock");
            return false;
        }

        return true;
    }

    private void saveCompanyProfileToDb(CompanyProfileResp companyProfileResp) {
        CompanyProfile companyProfile = new CompanyProfile();
        BeanUtils.copyProperties(companyProfileResp.getData().getCompanyProfile(), companyProfile);
        companyProfile.setCompanyProfileDesc(companyProfile.getCompanyProfileDesc().substring(0, 500));
        try {
            companyProfileRepository.save(companyProfile);
        } catch (Exception e) {
            log.info("Failed to save company profile to DB: {} to DB --> {} - {}"
                    , companyProfile, e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveCompanyProfile(String symbol) {
        CompanyProfileReqModel companyProfileReqModel = new CompanyProfileReqModel();

        companyProfileReqModel.setSymbol(symbol);
        try {
            CompanyProfileResp companyProfileResp =
                    ssiClient.getCompanyProfile(companyProfileReqModel);
            CompletableFuture.runAsync(() -> saveCompanyProfileToDb(companyProfileResp));
        } catch (Exception e) {
            log.info("Error when crawl company profile --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void crawlCompanyProfile() {
        List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
        listSingleStock.parallelStream().forEach(item -> crawlAndSaveCompanyProfile(item.getCode()));
    }

    private void saveNewsToDb(NewsModel newsModel) {
        News news = new News();
        BeanUtils.copyProperties(newsModel, news);
        if (news.getFullContent().length() > 900)
            news.setFullContent(news.getFullContent().substring(0, 500));

        try {
            newsRepository.save(news);
        } catch (Exception e) {
            log.info("Failed to save news to DB --> {} -{}", e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveNews(String symbol) {
        NewsReqModel newsReqModel = new NewsReqModel();
        newsReqModel.setSymbol(symbol);
        try {
            newsReqModel.setFromDate("01/01/1970");
            newsReqModel.setToDate(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            log.info("Error when convert String to Object");
        }
        newsReqModel.setOffset(1);
        newsReqModel.setSize(1000000);

        NewsResp newsResp = ssiClient.getAllNews(newsReqModel);

        newsResp.getData().getNews().getDataList().forEach(this::saveNewsToDb);
    }

    @Override
    public void crawlNews() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveNews(item.getCode()));
        });
    }

    private void saveDividendToDb(String symbol, CashDividendListModel cashDividendListModel) {
        CashDividend cashDividend = new CashDividend();
        BeanUtils.copyProperties(cashDividendListModel, cashDividend);
        cashDividend.setSymbol(symbol);

        try {
            cashDividendRepository.save(cashDividend);
        } catch (Exception e) {
            log.info("Failed to save cashDividend to Db, --> {} - {} ", e.getMessage(), e.getCause());
        }
    }

    private void saveAssetToDb(String symbol, AssetListModel assetListModel) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetListModel, asset);
        asset.setSymbol(symbol);

        try {
            assetRepository.save(asset);
        } catch (Exception e) {
            log.info("Failed to save asset to Db --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    private void saveOwnerCapital(String symbol, CapitalListModel capitalListModel) {
        Capital capital = new Capital();
        BeanUtils.copyProperties(capitalListModel, capital);
        capital.setSymbol(symbol);

        try {
            capitalRepository.save(capital);
        } catch (Exception e) {
            log.info("Failed to save capital to Db --> {} - {}", e.getMessage(), e.getCause());
        }
    }

    private void crawlAndSaveCapAndDividend(String symbol) {
        CapAndDividendReqModel capAndDividendReqModel = new CapAndDividendReqModel();
        capAndDividendReqModel.setSymbol(symbol);

        try {
            CapAndDividendResp capAndDividendResp = ssiClient.getCapAndDividend(capAndDividendReqModel);
            CapAndDividendDataGroup capAndDividendDataGroup = capAndDividendResp.getData()
                    .getTabCapitalAndDividend().getCapCashWrapper().getDataGroup();
            capAndDividendDataGroup.getCashDividendList().forEach(item -> saveDividendToDb(symbol, item));
            capAndDividendDataGroup.getAssetList().forEach(item -> saveAssetToDb(symbol, item));
            capAndDividendDataGroup.getOwnerCapitalList().forEach(item -> saveOwnerCapital(symbol, item));
        } catch (Exception e) {
            log.info("Failed to crawl capAndDividend {} --> {} - {}", symbol, e.getMessage(), e.getCause());
        }
    }

    @Override
    public void crawlCapAndDividend() {
        CompletableFuture.runAsync(() -> {
            List<SingleStockModel> listSingleStock = ssiClient.getAllStock().getData();
            listSingleStock.forEach(item -> crawlAndSaveCapAndDividend(item.getCode()));
        });
    }
}
