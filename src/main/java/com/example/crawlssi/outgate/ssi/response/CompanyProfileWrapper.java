package com.example.crawlssi.outgate.ssi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyProfileWrapper {

    @JsonProperty(value = "companyProfile")
    private CompanyProfileModel companyProfile;

}
