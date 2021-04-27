package com.example.crawlssi.outgate.ssi;

import com.example.crawlssi.outgate.ssi.response.ListLikeResp;
import com.example.crawlssi.outgate.ssi.response.SingleStockModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ssiClient", url = "${url.ssi.host}")
public interface SsiClient {

    @GetMapping("${url.ssi.endpoint.defaultallstock}")
    ListLikeResp<SingleStockModel> getAllStock();

}
