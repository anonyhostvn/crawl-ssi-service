package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShareHolderModel {

    @JsonProperty("symbol")
    private String symbol;


    @JsonProperty("name")
    private String name;


    @JsonProperty("quantity")
    private BigDecimal quantity;


    @JsonProperty("percentage")
    private BigDecimal percentage;


    @JsonProperty("publicdate")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date publicDate;


    @JsonProperty("ownershiptypecode")
    private String ownerShipTypeCode;


    @Column(name = "type")
    private String type;

}
