//package app.hello;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class GreetingSmthController {
//
//    @RequestMapping("/smth")
//    public Greeting postGreeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Greeting("Smth, " + name);
//    }
//}