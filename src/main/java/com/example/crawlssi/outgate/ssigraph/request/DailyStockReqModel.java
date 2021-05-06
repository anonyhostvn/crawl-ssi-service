package com.example.crawlssi.outgate.ssigraph.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyStockReqModel {
//
//    public DailyStockReqModel() {
//        this.operationName = "stockPrice";
//        this.query = "query stockPrice($symbol: String!, $size: Int, $offset: Int, $fromDate: String, $toDate: String) {\\n  stockPrice(symbol: $symbol, size: $size, offset: $offset, fromDate: $fromDate, toDate: $toDate)\\n}\\n";
//        this.variables = new DailyStockVariable();
//    }
//
//    @JsonProperty("operationName")
//    private String operationName;
//
//    @JsonProperty("query")
//    private String query;
//
//    @JsonProperty("variables")
//    private DailyStockVariable variables;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("offset")
    private Integer offset;

}
