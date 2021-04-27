package com.example.crawlssi.repository.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 15949425370209050L;

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME", updatable = false)
    private Date createdTime;

    @Basic
    @Column(name = "CREATOR_ID", updatable = false)
    private Long creatorId;

    @Basic
    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedTime;

    @Basic
    @Column(name = "LAST_UPDATED_ID")
    private Long lastUpdatedId;

    @Basic
    @Column(name = "IS_ACTIVED")
    private Boolean isActived = true;

    @Basic
    @Column(name = "IS_DELETED")
    private Boolean isDeleted = false;

}
