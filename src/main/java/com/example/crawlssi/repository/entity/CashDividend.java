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
@Table(name="AD_CASH_DIVIDEND")
public class CashDividend implements Serializable {

    private static final long serialVersionUID = -4055285284980328850L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "YEAR")
    private Integer year;


    @Column(name = "VALUE_PER_SHARE")
    private BigDecimal valuePerShare;


}
