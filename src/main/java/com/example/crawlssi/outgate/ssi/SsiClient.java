package com.example.crawlssi.outgate.ssi;

import com.example.crawlssi.outgate.ssi.request.CompanyProfileReqModel;
import com.example.crawlssi.outgate.ssi.request.NewsReqModel;
import com.example.crawlssi.outgate.ssi.response.CompanyProfileResp;
import com.example.crawlssi.outgate.ssi.response.ListLikeResp;
import com.example.crawlssi.outgate.ssi.response.NewsResp;
import com.example.crawlssi.outgate.ssi.response.SingleStockModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ssiClient", url = "${url.ssi.host}")
public interface SsiClient {

    @GetMapping("${url.ssi.endpoint.defaultallstock}")
    ListLikeResp<SingleStockModel> getAllStock();

    @PostMapping("${url.ssi.endpoint.companyprofile}")
    CompanyProfileResp getCompanyProfile(
            @RequestBody CompanyProfileReqModel companyProfileReqModel
    );

    @PostMapping("${url.ssi.endpoint.news}")
    NewsResp getAllNews(
            @RequestBody NewsReqModel newsReqModel
    );

}
