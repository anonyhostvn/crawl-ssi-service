package com.example.crawlssi.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "AD_FINANCE_INDICATOR")
public class FinanceIndicator implements Serializable {

    private static final long serialVersionUID = 6048480606422743164L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "REVENUE")
    private BigDecimal revenue;

    @Column(name = "PROFIT")
    private BigDecimal profit;

    @Column(name = "YEAR_REPORT")
    private Integer yearReport;

    @Column(name = "LENGTH_REPORT")
    private Integer lengthReport;

    @Column(name = "EPS")
    private BigDecimal eps;

    @Column(name = "DILUTE_DEPS")
    private BigDecimal diluteDeps;

    @Column(name = "PE")
    private BigDecimal pe;

    @Column(name = "ROE")
    private BigDecimal roe;

    @Column(name = "ROA")
    private BigDecimal roa;

    @Column(name = "ROIC")
    private BigDecimal roic;

    @Column(name = "GROSS_PROFIT_MARGIN")
    private BigDecimal grossProfitMargin;

    @Column(name = "NET_PROFIT_MARGIN")
    private BigDecimal netProfitMargin;

    @Column(name = "DEBT_EQUITY")
    private BigDecimal debtEquity;

    @Column(name = "DEBT_ASSET")
    private BigDecimal debtAsset;

    @Column(name = "QUICK_RATIO")
    private BigDecimal quickRatio;

    @Column(name = "CURRENT_RATIO")
    private BigDecimal currentRatio;

    @Column(name = "PB")
    private BigDecimal pb;

}
