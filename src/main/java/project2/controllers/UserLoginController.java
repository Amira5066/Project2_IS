package project2.controllers;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project2.data.UserRepository;
import project2.models.Event;
import project2.models.EventCategory;
import project2.models.User;

@Controller
public class UserLoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("sign_in")
    public String displaySignInForm(Model model) {
        return "sign_in";
    }

    @PostMapping("sign_in")
    public String processSignInForm(@RequestParam() String username, @RequestParam() String password, Model model) {

        if (username.isEmpty() || password.isEmpty() ) {
            model.addAttribute("error", "Username and password are required.");
            return "sign_in";
        }
        User user = new User();
        user.setUsername(username);
        user.encodeAndSetPassword(password);
//        if (userRepository.findAll(user))
        userRepository.save(user);
        return "sign_in";
//        return "redirect:/events";
    }
}
