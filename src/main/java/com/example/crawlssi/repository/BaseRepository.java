package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, K extends Serializable> extends JpaRepository<T, K> {

}
