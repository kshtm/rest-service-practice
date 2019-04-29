package app.controller;

import app.configuration.AspectAnnotation;
import app.model.Greeting;
import app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class GreetingController {

    @Value("${my.prop}")
    String myProp;

    @Autowired
    ApplicationContext context;

    @Autowired
    GreetingService greetingService;

    @AspectAnnotation
    @PostMapping("/")
    public Greeting postGreeting(@RequestParam(value = "greeting", defaultValue = "World") String greeting) {
        return greetingService.save(greeting);
    }

    @GetMapping(value = "/greeting/{id}")
    public Greeting getGreeting(@PathVariable long id) {
        return greetingService.findById(id);
    }

    @GetMapping(value = "/test")
    public String getTest() {
        return "Test";
    }

    @GetMapping("/greetings")
    public List<Greeting> getGreetings() {
        return greetingService.findAll();
    }


}

