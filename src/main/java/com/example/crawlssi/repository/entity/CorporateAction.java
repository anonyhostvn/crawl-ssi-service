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
@Table(name="AD_CORPORATE")
public class CorporateAction implements Serializable {

    private static final long serialVersionUID = 6095558272480138242L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "SYMBOL")
    private String symbol;


    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "ex_right_date")
    private Date exRightDate;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "public_date")
    private Date publicDate;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "event_list_code")
    private String eventListCode;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "ratio")
    private BigDecimal ratio;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_code")
    private String eventCode;

}
