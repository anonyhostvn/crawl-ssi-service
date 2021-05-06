package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.DailyStockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStockPriceRepository extends JpaRepository<DailyStockPrice, Long> {
}
