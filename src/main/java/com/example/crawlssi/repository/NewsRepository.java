package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long>  {
}
