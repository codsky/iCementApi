package com.icement.api.iCement.ApiHealthCheck.Services;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApiHealthCheckService {

    private final MongoTemplate mongoTemplate;

    public ApiHealthCheckService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String checkMongoDBConnectionStatus() {
        return mongoTemplate.getDb().getName() != null ? "MongoDB is UP" : "MongoDB is DOWN";
    }

}
