package app.service;

import app.model.Greeting;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient
public interface GreetingServiceProxy {

    @GetMapping("/greeting/{id}")
    public Greeting findById(@PathVariable long id);

    @GetMapping("/greetings")
    public List<Greeting> findAll();
}
