package com.example.crawlssi.outgate.ssigraph.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyStockVariable {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

}
