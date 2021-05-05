package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NewsModel {

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "shortcontent")
    private String shortContent;

    @JsonProperty(value = "fullcontent")
    private String fullContent;

    @JsonProperty(value = "imageurl")
    private String imageUrl;

    @JsonProperty(value = "createdate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date createDate;

    @JsonProperty(value = "newssource")
    private String newsSource;

    @JsonProperty(value = "sourcecode")
    private String sourceCode;

    @JsonProperty(value = "newssourcelink")
    private String newsSourceLink;

    @JsonProperty(value = "updatedate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date updateDate;

}
