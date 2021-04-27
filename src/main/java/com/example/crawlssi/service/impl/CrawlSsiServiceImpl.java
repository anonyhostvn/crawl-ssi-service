package com.example.crawlssi.service.impl;

import com.example.crawlssi.exceptions.CustomBusinessLogicException;
import com.example.crawlssi.factory.resfact.ResponseStatusEnum;
import com.example.crawlssi.outgate.ssi.SsiClient;
import com.example.crawlssi.outgate.ssi.response.SingleStockModel;
import com.example.crawlssi.repository.TradingExchangeRepository;
import com.example.crawlssi.repository.entity.TradingExchange;
import com.example.crawlssi.service.CrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CrawlSsiServiceImpl implements CrawlSsiService {

    private final SsiClient ssiClient;
    private final TradingExchangeRepository tradingExchangeRepository;

    @Autowired
    public CrawlSsiServiceImpl(
            SsiClient ssiClient,
            TradingExchangeRepository tradingExchangeRepository
    ) {
        this.tradingExchangeRepository = tradingExchangeRepository;
        this.ssiClient = ssiClient;
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
    public Boolean crawlAllStock() {
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
}
