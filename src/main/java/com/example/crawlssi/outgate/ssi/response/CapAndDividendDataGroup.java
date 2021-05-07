package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CapAndDividendDataGroup {

    @JsonProperty("assetlistList")
    private List<AssetListModel> assetList;

    @JsonProperty("cashdividendlistList")
    private List<CashDividendListModel> cashDividendList;

    @JsonProperty("ownercapitallistList")
    private List<CapitalListModel> ownerCapitalList;

}
