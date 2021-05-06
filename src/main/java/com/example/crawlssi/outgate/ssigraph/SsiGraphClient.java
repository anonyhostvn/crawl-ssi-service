package com.example.crawlssi.outgate.ssigraph;

import com.example.crawlssi.outgate.ssigraph.request.DailyStockReqModel;
import com.example.crawlssi.outgate.ssigraph.response.DailyStockResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ssiClientGraph", url = "${url.ssi.host}")
public interface SsiGraphClient {

    @PostMapping("${url.ssi.endpoint.stockPrice}")
    DailyStockResp getDailyStockData(@RequestBody DailyStockReqModel dailyStockReqModel);

}
