package app.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyKafkaListenerImpl {

    @KafkaListener(topics = "MyTopic", groupId = "1")
    public void listen(String message) {
        System.err.println("Received Messasge in group 1: " + message);
    }
}
