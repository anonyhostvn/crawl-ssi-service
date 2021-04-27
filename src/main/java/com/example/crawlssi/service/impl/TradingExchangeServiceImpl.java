package com.example.crawlssi.service.impl;

import com.example.crawlssi.model.response.GetAllTradingExchangeResponse;
import com.example.crawlssi.repository.TradingExchangeRepository;
import com.example.crawlssi.service.TradingExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradingExchangeServiceImpl implements TradingExchangeService {

    private final TradingExchangeRepository tradingExchangeRepository;

    @Autowired
    public TradingExchangeServiceImpl(TradingExchangeRepository tradingExchangeRepository){
        this.tradingExchangeRepository = tradingExchangeRepository;
    }

    @Override
    public GetAllTradingExchangeResponse getListTradingExchange() {
        GetAllTradingExchangeResponse getAllTradingExchangeResponse = new GetAllTradingExchangeResponse();
        getAllTradingExchangeResponse.setTradingExchangeList(tradingExchangeRepository.findAll());
        return getAllTradingExchangeResponse;
    }
}
