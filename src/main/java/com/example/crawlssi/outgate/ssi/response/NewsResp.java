package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewsResp {

    @JsonProperty(value = "data")
    private NewsWrapper data;
}
