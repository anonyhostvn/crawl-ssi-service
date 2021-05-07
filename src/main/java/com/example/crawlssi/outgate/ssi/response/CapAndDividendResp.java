package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CapAndDividendResp {

    @JsonProperty("data")
    private CapAndDividendWrapper data;

}
