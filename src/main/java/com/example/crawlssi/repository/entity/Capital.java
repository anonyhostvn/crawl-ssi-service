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
@Table(name="AD_CAPITAL")
public class Capital implements Serializable {


    private static final long serialVersionUID = 3453557579540951265L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "OWNER_CAPITAL")
    private BigDecimal ownerCapital;

}
