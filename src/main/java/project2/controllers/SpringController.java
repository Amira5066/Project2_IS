package project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringController {
    @GetMapping("helloSpring")
    @ResponseBody
    public String hello() {
        return "Hello Spring!";
    }

    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye Spring!";
    }

    // Handles request of the form /hello?name=LaunchCode
    @GetMapping("hello")
    @ResponseBody
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello " + name + "!";
    }

    // Handles request of the form /hello/LaunchCode
    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloWithPathParam(@PathVariable String name) {
        return "Hello " + name + "!";
    }
}
