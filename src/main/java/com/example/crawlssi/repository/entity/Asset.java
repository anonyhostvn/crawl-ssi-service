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
@Table(name="AD_ASSET")
public class Asset implements Serializable {

    private static final long serialVersionUID = -8021526033246019017L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "ASSET")
    private BigDecimal asset;

}
