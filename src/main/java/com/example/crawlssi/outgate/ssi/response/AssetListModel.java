package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetListModel {

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("asset")
    private BigDecimal asset;

}
