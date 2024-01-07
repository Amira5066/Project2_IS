package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project2.repository.RoleRepository;
import project2.repository.UserRepository;
import project2.models.Role;
import project2.models.User;
import project2.service.RoleService;
import project2.service.UserService;

import java.util.Optional;

@Controller
public class UserLoginController {

    private UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("sign_in")
    public String displaySignInForm() {
        return "sign_in";
    }

    @PostMapping("sign_in")
    public String processSignInForm(@RequestParam() String username, @RequestParam() String password, Model model) {

        if (username.isEmpty() || password.isEmpty() ) {
            model.addAttribute("error", "Username and password are required.");
            return "sign_in";
        }
        if (!userService.saveUser(username, password)) {
            model.addAttribute("error", "Username already exists.");
            return "sign_in";
        }
        return "redirect:/login";
    }
}
