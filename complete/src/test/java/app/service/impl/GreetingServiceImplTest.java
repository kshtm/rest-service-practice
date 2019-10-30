package app.service.impl;

import app.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GreetingServiceImplTest {

    @Autowired
    GreetingService greetingService;

    @Test
    void save() {
        greetingService.save("Test content");
    }
}