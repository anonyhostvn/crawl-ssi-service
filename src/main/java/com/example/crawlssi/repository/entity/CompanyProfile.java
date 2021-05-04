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
@Table(name="AD_COMPANY_PROFILE")
public class CompanyProfile implements Serializable {

    private static final long serialVersionUID = 7610597125930466018L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "SYMBOL", unique = true)
    private String symbol;

    @Column(name = "SUB_SECTOR_CODE")
    private String subSectorCode;

    @Column(name = "INDUSTRY_NAME")
    private String industryName;

    @Column(name = "SUPER_SECTOR")
    private String superSector;

    @Column(name = "SECTOR")
    private String sector;

    @Column(name = "SUB_SECTOR")
    private String subSector;

    @Column(name = "FOUNDING_DATE")
    private Date foundingDate;

    @Column(name = "CHARTER_CAPITAL")
    private BigDecimal charterCapital;

    @Column(name = "NUMBER_OF_EMPLOYEE")
    private Integer numberOfEmployee;

    @Column(name = "BANK_NUMBER_OF_BRANCH")
    private Integer bankNumberOfBranch;

    @Column(name = "COMPANY_PROFILE")
    private String companyProfileDesc;

    @Column(name = "LISTING_DATE")
    private Date listingDate;

    @Column(name = "EXCHANGE")
    private String exchange;

    @Column(name = "FIRST_PRICE")
    private BigDecimal firstPrice;

    @Column(name = "ISSUE_SHARE")
    private BigDecimal issueShare;

    @Column(name = "LISTED_VALUE")
    private BigDecimal listedValue;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "TYPE_NAME")
    private String typeName;
}
