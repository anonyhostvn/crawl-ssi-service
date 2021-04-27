package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.TradingExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TradingExchangeRepository extends JpaRepository<TradingExchange, Long> {

    Optional<List<TradingExchange>> findByExchangeCode(String exchangeCode);

}
