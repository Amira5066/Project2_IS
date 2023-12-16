package project2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    // Accepts both GET and POST requests.
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
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

    @GetMapping("form")
    @ResponseBody
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
