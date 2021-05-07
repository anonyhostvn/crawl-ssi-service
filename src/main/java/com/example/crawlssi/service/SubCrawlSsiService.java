package com.example.crawlssi.service;

import org.springframework.stereotype.Service;

@Service
public interface SubCrawlSsiService {

    void crawlCompanyFinance();

    void crawlStockDailyFromSsiGraph();

    void crawlShareHolder();

    void crawlCorporateAction();

}
