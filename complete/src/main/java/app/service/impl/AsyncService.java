package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AsyncService {

//    @Autowired
//    WebClient webClient;

    @Scheduled(fixedRate = 1000)
    public void doInSchedule() {
//        webClient.get()
//                .uri("localhost:9090/test")
//                .
        System.err.println("Scheduled");
    }

}
