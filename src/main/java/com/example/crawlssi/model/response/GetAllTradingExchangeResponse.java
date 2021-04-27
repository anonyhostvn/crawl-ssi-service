package com.example.crawlssi.model.response;

import com.example.crawlssi.repository.entity.TradingExchange;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllTradingExchangeResponse {

    @JsonProperty("tradingExchangeList")
    private List<TradingExchange> tradingExchangeList;

}
