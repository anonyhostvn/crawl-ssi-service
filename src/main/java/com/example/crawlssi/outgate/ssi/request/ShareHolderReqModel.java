package com.example.crawlssi.outgate.ssi.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShareHolderReqModel {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("offset")
    private Integer offset;

}
