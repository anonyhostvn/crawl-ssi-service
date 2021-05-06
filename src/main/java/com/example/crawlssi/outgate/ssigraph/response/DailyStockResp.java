package com.example.crawlssi.outgate.ssigraph.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyStockResp {

    @JsonProperty("data")
    private DailyStockPaging data;

}
