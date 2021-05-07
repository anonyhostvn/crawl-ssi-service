package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShareHolderPaging {

    @JsonProperty("shareholders")
    private ShareHolderWrapper shareHolders;

}
