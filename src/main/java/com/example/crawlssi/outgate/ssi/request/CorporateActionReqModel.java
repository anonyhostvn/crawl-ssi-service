package com.example.crawlssi.outgate.ssi.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CorporateActionReqModel {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("dateType")
    private String dateType;

    @JsonProperty("eventCode")
    private String eventCode;

}
