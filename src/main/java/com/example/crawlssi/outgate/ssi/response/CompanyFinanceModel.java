package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyFinanceModel {

    @JsonProperty(value = "revenue")
    private BigDecimal revenue;

    @JsonProperty(value = "profit")
    private BigDecimal profit;

    @JsonProperty(value = "yearreport")
    private Integer yearReport;

    @JsonProperty(value = "lengthreport")
    private Integer lengthReport;

    @JsonProperty(value = "eps")
    private BigDecimal eps;

    @JsonProperty(value = "dilutedeps")
    private BigDecimal diluteDeps;

    @JsonProperty(value = "pe")
    private BigDecimal pe;

    @JsonProperty(value = "roe")
    private BigDecimal roe;

    @JsonProperty(value = "roa")
    private BigDecimal roa;

    @JsonProperty(value = "roic")
    private BigDecimal roic;

    @JsonProperty(value = "grossprofitmargin")
    private BigDecimal grossProfitMargin;

    @JsonProperty(value = "netprofitmargin")
    private BigDecimal netProfitMargin;

    @JsonProperty(value = "debtequity")
    private BigDecimal debtEquity;

    @JsonProperty(value = "debtasset")
    private BigDecimal debtAsset;

    @JsonProperty(value = "quickratio")
    private BigDecimal quickRatio;

    @JsonProperty(value = "currentratio")
    private BigDecimal currentRatio;

    @JsonProperty(value = "pb")
    private BigDecimal pb;

}
