package app.controller;

import app.configuration.AspectAnnotation;
import app.model.Greeting;
import app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/greetings")
    public List<Greeting> getGreetings() {
        return greetingService.findAll();
    }



}

