package com.example.crawlssi.outgate.ssi.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CapAndDividendReqModel {

    @JsonProperty("symbol")
    private String symbol;

}
