package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project2.data.RoleRepository;
import project2.data.UserRepository;
import project2.models.Role;
import project2.models.User;

import java.util.Optional;

@Controller
public class UserLoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists.");
            return "sign_in";
        }

        User user = new User();
        user.setUsername(username);
        user.encodeAndSetPassword(password);
        Optional<Role> userRole = roleRepository.findByRole(Role.USER);
        if (!userRole.isPresent()) {
            model.addAttribute("error", "Role not found.");
            return "sign_in";
        }
        user.setRole(userRole.get());
        userRepository.save(user);
        return "redirect:/login";
    }
}
