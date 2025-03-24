package com.icement.api.iCement.ApiHealthCheck.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icement.api.iCement.ApiHealthCheck.Services.ApiHealthCheckService;

@RestController
@RequestMapping("/api/health")
public class ApiHealthCheckController {

    private final ApiHealthCheckService apiHealthCheckService;

    public ApiHealthCheckController(ApiHealthCheckService apiHealthCheckService) {
        this.apiHealthCheckService = apiHealthCheckService;
    }

    @RequestMapping("/mongo")
    public String isMongoUp() {
        return apiHealthCheckService.checkMongoDBConnectionStatus();
    }

}
