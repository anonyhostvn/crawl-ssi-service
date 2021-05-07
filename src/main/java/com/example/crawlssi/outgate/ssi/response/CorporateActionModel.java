package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CorporateActionModel {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("eventname")
    private String eventName;

    @JsonProperty("exrightdate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date exRightDate;

    @JsonProperty("recorddate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date recordDate;

    @JsonProperty("issuedate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date issueDate;

    @JsonProperty("eventtitle")
    private String eventTitle;

    @JsonProperty("publicdate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date publicDate;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("eventlistcode")
    private String eventListCode;

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("ratio")
    private BigDecimal ratio;

    @JsonProperty("eventdescription")
    private String eventDescription;

    @JsonProperty("eventcode")
    private String eventCode;

}
