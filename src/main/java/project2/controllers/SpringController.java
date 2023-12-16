package project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("spring")
public class SpringController {
    // lives /spring/helloSpring
    @GetMapping("helloSpring")
    public String hello() {
        return "Hello Spring!";
    }

    // lives /spring/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye Spring!";
    }

    // Handles request of the form /hello?name=LaunchCode
    // Accepts both GET and POST requests.
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello " + name + "!";
    }

    // Handles request of the form /hello/LaunchCode
    @GetMapping("hello/{name}")
    public String helloWithPathParam(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("form")
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='hello' method='post'>" + // submit a request to /hello
                "<input type='text' name='name'>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
