package com.icement.api.iCement.Domains.ApiHealthCheck.Services;

import org.springframework.stereotype.Service;

@Service
public class ApiHealthCheckService {

    public ApiHealthCheckService() {
    }

    public String isApiActive() {
        return "iCement API is active";
    }

}
