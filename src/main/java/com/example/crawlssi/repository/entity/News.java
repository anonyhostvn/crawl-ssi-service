package com.example.crawlssi.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "AD_NEWS")
public class News implements Serializable {

    private static final long serialVersionUID = -6667234377826965949L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "SYMBOL", unique = true)
    private String symbol;

    @Column(name = "TITLE", unique = true)
    private String title;

    @Column(name = "IMAGE_URL", unique = true)
    private String imageUrl;

    @Column(name = "SHORT_CONTENT", unique = true)
    private String shortContent;

    @Column(name = "FULL_CONTENT", unique = true)
    private String fullContent;

    @Column(name = "NEWS_SOURCE", unique = true)
    private String newsSource;

    @Column(name = "SOURCE_CODE", unique = true)
    private String sourceCode;

    @Column(name = "NEWS_SOURCE_LINK", unique = true)
    private String newsSourceLink;

}
