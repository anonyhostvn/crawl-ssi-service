package com.example.crawlssi.service;

import org.springframework.stereotype.Service;

@Service
public interface CrawlSsiService {

    boolean crawlAllStock();

    void crawlCompanyProfile();

    void crawlNews();

}
