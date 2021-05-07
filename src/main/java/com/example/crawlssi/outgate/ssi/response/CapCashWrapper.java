package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CapCashWrapper {

    @JsonProperty("datagroup")
    private CapAndDividendDataGroup dataGroup;

}
