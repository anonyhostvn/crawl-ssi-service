package com.example.crawlssi.outgate.ssigraph.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DailyStockPriceModel {

    @JsonProperty(value = "tradingdate")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date tradingDate;

    @JsonProperty("pricechange")
    private BigDecimal priceChange;

    @JsonProperty("perpricechange")
    private BigDecimal perPriceChange;

    @JsonProperty("ceilingprice")
    private BigDecimal ceilingPrice;

    @JsonProperty("floorprice")
    private BigDecimal floorPrice;

    @JsonProperty("refprice")
    private BigDecimal refPrice;

    @JsonProperty("openprice")
    private BigDecimal openPrice;

    @JsonProperty("highestprice")
    private BigDecimal highestPrice;

    @JsonProperty("lowestprice")
    private BigDecimal lowestPrice;

    @JsonProperty("closeprice")
    private BigDecimal closePrice;

    @JsonProperty("averageprice")
    private BigDecimal averagePrice;

    @JsonProperty("closepriceadjusted")
    private BigDecimal closePriceAdjusted;

    @JsonProperty("totalmatchvol")
    private BigDecimal totalMatchVol;

    @JsonProperty("totalmatchval")
    private BigDecimal totalMatchVal;

    @JsonProperty("totaldealval")
    private BigDecimal totalDealVal;

    @JsonProperty("totaldealvol")
    private BigDecimal totalDealVol;

    @JsonProperty("foreignbuyvoltotal")
    private BigDecimal foreignBuyVolTotal;

    @JsonProperty("foreigncurrentroom")
    private BigDecimal foreignCurrentRoom;

    @JsonProperty("foreignsellvoltotal")
    private BigDecimal foreignSellVolTotal;

    @JsonProperty("foreignbuyvaltotal")
    private BigDecimal foreignBuyValTotal;

    @JsonProperty("foreignsellvaltotal")
    private BigDecimal foreignSellValTotal;

    @JsonProperty("totalbuytrade")
    private BigDecimal totalBuyTrade;

    @JsonProperty("totalbuytradevol")
    private BigDecimal totalBuyTradeVol;

    @JsonProperty("totalselltrade")
    private BigDecimal totalSellTrade;

    @JsonProperty("totalselltradevol")
    private BigDecimal totalSellTradeVol;

    @JsonProperty("netbuysellvol")
    private BigDecimal netBuySellVol;

    @JsonProperty("netbuysellval")
    private BigDecimal netBuySellVal;

}
