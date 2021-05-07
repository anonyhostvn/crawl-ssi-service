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
@Table(name="AD_SHARE_HOLDER")
public class ShareHolder implements Serializable {

    private static final long serialVersionUID = -343280901280643633L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "OWNER_SYMBOL")
    private String ownerSymbol;


    @Column(name = "SYMBOL")
    private String symbol;


    @Column(name = "NAME")
    private String name;


    @Column(name = "QUANTITY")
    private BigDecimal quantity;


    @Column(name = "PERCENTAGE")
    private BigDecimal percentage;


    @Column(name = "PUBLIC_DATE")
    private Date publicDate;


    @Column(name = "OWNER_SHIP_TYPE_CODE")
    private String ownerShipTypeCode;

    @Column(name = "TYPE")
    private String type;

}
