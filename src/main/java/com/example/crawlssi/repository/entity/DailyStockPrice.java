package com.example.crawlssi.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="TB_DAILY_STOCK_INFO")
public class DailyStockPrice implements Serializable {

    private static final long serialVersionUID = -2515104871572768855L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "exchangeid")
    private Long exchangeId;

    @Column(name = "exchange")
    private String exchange;


    @Column(name = "tradingdate")
    private Date tradingDate;

    @Column(name = "pricechange")
    private BigDecimal priceChange;

    @Column(name = "pricechangepercentage")
    private BigDecimal perPriceChange;

    @Column(name = "ceilingprice")
    private BigDecimal ceilingPrice;

    @Column(name = "floorprice")
    private BigDecimal floorPrice;

    @Column(name = "refprice")
    private BigDecimal refPrice;

    @Column(name = "openprice")
    private BigDecimal openPrice;

    @Column(name = "highprice")
    private BigDecimal highestPrice;

    @Column(name = "lowprice")
    private BigDecimal lowestPrice;

    @Column(name = "closeprice")
    private BigDecimal closePrice;

    @Column(name = "avgprice")
    private BigDecimal averagePrice;

    @Column(name = "closeprice_adjusted")
    private BigDecimal closePriceAdjusted;

    @Column(name = "TOTALQTTY")
    private BigDecimal totalMatchVol;

    @Column(name = "TOTALVALUE")
    private BigDecimal totalMatchVal;

}
