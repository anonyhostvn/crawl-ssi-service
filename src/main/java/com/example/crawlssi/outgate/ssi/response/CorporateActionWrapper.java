package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CorporateActionWrapper {

    @JsonProperty("dataList")
    private List<CorporateActionModel> dataList;

}
