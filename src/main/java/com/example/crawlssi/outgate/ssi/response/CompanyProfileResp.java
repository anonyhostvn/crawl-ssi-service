package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyProfileResp {

    @JsonProperty(value = "data")
    private CompanyProfileWrapper data;

}
