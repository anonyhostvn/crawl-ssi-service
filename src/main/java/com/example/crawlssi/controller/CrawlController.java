package com.example.crawlssi.controller;


import com.example.crawlssi.factory.resfact.GeneralResponse;
import com.example.crawlssi.factory.resfact.GeneralResponseStatus;
import com.example.crawlssi.factory.resfact.ResponseFactory;
import com.example.crawlssi.factory.resfact.ResponseStatusEnum;
import com.example.crawlssi.service.CrawlSsiService;
import com.example.crawlssi.service.SubCrawlSsiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping(path = "/external/crawler/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrawlController {

    private final CrawlSsiService crawlSsiService;
    private final SubCrawlSsiService subCrawlSsiService;

    @Autowired
    public CrawlController(
            CrawlSsiService crawlSsiService,
            SubCrawlSsiService subCrawlSsiService
    ) {
        this.crawlSsiService = crawlSsiService;
        this.subCrawlSsiService = subCrawlSsiService;
    }

    @GetMapping("/crawl-trading-exchange")
    public ResponseEntity<GeneralResponse<Object>> getAllDocument() {
        if (crawlSsiService.crawlAllStock())
            return ResponseFactory.success();
        return ResponseFactory.success(new GeneralResponseStatus(ResponseStatusEnum.UNKNOWN_ERROR));
    }

    @GetMapping("/crawl-company-profile")
    public ResponseEntity<GeneralResponse<Object>> crawlCompanyProfileControl() {
        crawlSsiService.crawlCompanyProfile();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-news")
    public ResponseEntity<GeneralResponse<Object>> crawlNewsControl() {
        crawlSsiService.crawlNews();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-finance-indicator")
    public ResponseEntity<GeneralResponse<Object>> crawlFinanceIndicator() {
        subCrawlSsiService.crawlCompanyFinance();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-daily-stock")
    public ResponseEntity<GeneralResponse<Object>> crawlDailyStockPrice() {
        subCrawlSsiService.crawlStockDailyFromSsiGraph();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-shareholder")
    public ResponseEntity<GeneralResponse<Object>> crawlShareHolder() {
        subCrawlSsiService.crawlShareHolder();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-corporateaction")
    public ResponseEntity<GeneralResponse<Object>> crawlCorporateAction() {
        subCrawlSsiService.crawlCorporateAction();
        return ResponseFactory.success();
    }

    @GetMapping("/crawl-capanddividend")
    public ResponseEntity<GeneralResponse<Object>> crawlCapAndDividend() {
        crawlSsiService.crawlCapAndDividend();
        return ResponseFactory.success();
    }

}
