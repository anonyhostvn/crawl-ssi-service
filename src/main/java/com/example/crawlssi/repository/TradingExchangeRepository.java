package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.TradingExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradingExchangeRepository extends JpaRepository<TradingExchange, Long> {
}
