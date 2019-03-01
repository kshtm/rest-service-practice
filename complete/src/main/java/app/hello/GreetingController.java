package app.hello;

import app.configuration.AspectAnnotation;
import app.model.Greeting;
import app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/")
    public Greeting greeting(@RequestParam(value = "content", defaultValue = "World") String content) {
        return greetingService.save(content);
    }



}

