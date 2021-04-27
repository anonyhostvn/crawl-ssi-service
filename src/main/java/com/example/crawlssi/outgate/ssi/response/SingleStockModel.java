package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SingleStockModel {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "exchange")
    private String exchange;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "stockNo")
    private String stockNo;

    @JsonProperty(value = "clientName")
    private String clientName;

    @JsonProperty(value = "clientNameEn")
    private String clientNameEn;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "securityName")
    private String securityName;
}
