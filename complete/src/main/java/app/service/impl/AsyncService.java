package app.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Scheduled(fixedRate = 1000)
    public void doInSchedule() {
        System.err.println("Schedule");
    }

}
