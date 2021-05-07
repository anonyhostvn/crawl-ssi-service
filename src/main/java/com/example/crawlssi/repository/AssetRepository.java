package com.example.crawlssi.repository;

import com.example.crawlssi.repository.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository  extends JpaRepository<Asset, Long> {
}
