package com.example.crawlssi.outgate.ssigraph.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyStockPaging {

    @JsonProperty("stockPrice")
    private DailyStockWrapper stockPrice;

}
