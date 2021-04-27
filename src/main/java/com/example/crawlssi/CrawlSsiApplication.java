package com.example.crawlssi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrawlSsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlSsiApplication.class, args);
    }

}
