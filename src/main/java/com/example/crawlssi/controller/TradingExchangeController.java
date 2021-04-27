package com.example.crawlssi.controller;


import com.example.crawlssi.factory.resfact.GeneralResponse;
import com.example.crawlssi.factory.resfact.ResponseFactory;
import com.example.crawlssi.model.response.GetAllTradingExchangeResponse;
import com.example.crawlssi.service.TradingExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(path = "/external/trading-exchange/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class TradingExchangeController {

    private final TradingExchangeService tradingExchangeService;

    public TradingExchangeController(TradingExchangeService tradingExchangeService) {
        this.tradingExchangeService = tradingExchangeService;
    }


    @GetMapping("/trading-exchange")
    public ResponseEntity<GeneralResponse<GetAllTradingExchangeResponse>> getAllDocument() {
        return ResponseFactory.success(tradingExchangeService.getListTradingExchange());
    }

}
