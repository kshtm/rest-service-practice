package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name, @PathVariable int id) {
        return new Greeting("Hello, " + name);
    }

//    @RequestMapping("/greeting/{id}")
//    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name, @PathVariable int id) {
//        return new Greeting(id, "Hello, " + name);
//    }
}
