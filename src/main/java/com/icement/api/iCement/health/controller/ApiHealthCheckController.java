package com.icement.api.iCement.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icement.api.iCement.health.service.ApiHealthCheckService;

@RestController
@RequestMapping("/api/health")
public class ApiHealthCheckController {

    private final ApiHealthCheckService apiHealthCheckService;

    public ApiHealthCheckController(ApiHealthCheckService apiHealthCheckService) {
        this.apiHealthCheckService = apiHealthCheckService;
    }

    @GetMapping("/status")
    public String isActive() {
        return apiHealthCheckService.isApiActive();
    }

}
