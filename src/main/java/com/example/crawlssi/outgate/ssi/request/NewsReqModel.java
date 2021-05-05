package com.example.crawlssi.outgate.ssi.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NewsReqModel {

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "offset")
    private Integer offset;

    @JsonProperty(value = "size")
    private Integer size;

    @JsonProperty(value = "fromDate")
    private String fromDate;

    @JsonProperty(value = "toDate")
    private String toDate;

}
