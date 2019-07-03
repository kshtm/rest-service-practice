package app.controller;

import app.configuration.AspectAnnotation;
import app.model.Greeting;
import app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


  @PostMapping("/")
  public Greeting doSmth(@RequestParam(value = "greeting", defaultValue = "World") String greeting) {
    return greetingService.save(greeting);
  }

  @GetMapping(value = "/test")
  public ResponseEntity<String> getTest() {
    return new ResponseEntity<String>("Test", HttpStatus.OK);
  }

  @GetMapping("/greetings")
  public List<Greeting> getGreetings() {
    return greetingService.findAll();
  }

  @GetMapping("/name/")
  public Greeting getGreetingByName(@RequestParam(value = "name") String content) {
    return greetingService.findByContent(content);
  }


}

