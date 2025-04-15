package com.icement.api.iCement;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= ICementApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
        .withExposedPorts(27017);

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        System.out.println("Mongo URI from container: " + mongoDBContainer.getReplicaSetUrl());
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

}
