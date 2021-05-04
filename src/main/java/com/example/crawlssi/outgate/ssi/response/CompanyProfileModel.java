package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CompanyProfileModel {

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "subsectorcode")
    private String subSectorCode;

    @JsonProperty(value = "industryname")
    private String industryName;


    @JsonProperty(value = "supersector")
    private String superSector;

    @JsonProperty(value = "sector")
    private String sector;

    @JsonProperty(value = "subsector")
    private String subSector;

    @JsonProperty(value = "foundingdate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date foundingDate;

    @JsonProperty(value = "chartercapital")
    private BigDecimal charterCapital;

    @JsonProperty(value = "numberofemployee")
    private Integer numberOfEmployee;

    @JsonProperty(value = "banknumberofbranch")
    private Integer bankNumberOfBranch;

    @JsonProperty(value = "companyprofile")
    private String companyProfileDesc;

    @JsonProperty(value = "listingdate")
    @JsonFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    private Date listingDate;

    @JsonProperty(value = "exchange")
    private String exchange;

    @JsonProperty(value = "firstprice")
    private BigDecimal firstPrice;

    @JsonProperty(value = "issueshare")
    private BigDecimal issueShare;

    @JsonProperty(value = "listedvalue")
    private BigDecimal listedValue;

    @JsonProperty(value = "companyname")
    private String companyName;

    @JsonProperty(value = "__typename")
    private String typeName;

}
