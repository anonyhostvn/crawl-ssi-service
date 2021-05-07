package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository  extends JpaRepository<Capital, Long> {
}
