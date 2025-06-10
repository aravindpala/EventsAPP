package com.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MongoConnectionTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testConnection() {
        System.out.println("Collections: " + mongoTemplate.getCollectionNames());
    }
}
