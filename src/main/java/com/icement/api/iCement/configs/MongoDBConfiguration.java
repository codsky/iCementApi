package com.icement.api.iCement.configs;

import java.time.Instant;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.icement.api.iCement.Auth")
@EnableMongoAuditing(dateTimeProviderRef = "dateTimeProvider")
public class MongoDBConfiguration {

    @Bean(name = "dateTimeProvider")
    DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(Instant.now());
    }
}
