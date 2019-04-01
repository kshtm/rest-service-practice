package app.service.impl;

import app.model.Greeting;
import app.service.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class GS2 implements GreetingService {
    @Override
    public Greeting save(String content) {
        throw new UnsupportedOperationException("None Bean");
    }
}
