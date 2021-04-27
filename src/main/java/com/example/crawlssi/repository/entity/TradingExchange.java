package com.example.crawlssi.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="CR_TRADING_EXCHANGE")
public class TradingExchange extends BaseEntity{

    private static final long serialVersionUID = 7610597125930466018L;

    @Column(name = "EXCHANGE_CODE")
    private String exchangeName;

    @Column(name = "EXCHANGE_NAME")
    private String exchangeCode;

}
