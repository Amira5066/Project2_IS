package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project2.data.*;
import project2.models.*;
import project2.models.dto.EventTagDTO;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String displayAllUsers(Model model) {
        Optional<Role> role = roleRepository.findByRole(Role.USER);
        model.addAttribute("users", userRepository.findAllByRole(role.get()));
        model.addAttribute("title", "All Users");

        return "users/index";
    }

    @GetMapping("edit")
    public String displayEditUserForm(@RequestParam() String username, Model model) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Username: " + username);
        } else {
            User user = result.get();
            model.addAttribute("title", user.getUsername()+ " Details");
            model.addAttribute("oldUsername", username);
            User newUser = new User();
            model.addAttribute("user", newUser);

            Iterable<Role> roles = roleRepository.findAll();
            model.addAttribute("roles", roles);
        }
        return "/users/edit";
    }

    @PostMapping("edit")
    public String processEditUserForm(@RequestParam() String username, @RequestParam() String oldUsername, @Validated @ModelAttribute User newUser, Model model) {
        Optional<User> existingUser = userRepository.findByUsername(oldUsername);
        System.out.println(oldUsername);
        System.out.println(existingUser.get().getUsername());
        if (existingUser.isPresent()) {
            if(newUser.getUsername().isEmpty()) {
                newUser.setUsername(oldUsername);
            }
            if(newUser.getPassword().isEmpty()) {
                newUser.setPassword(existingUser.get().getPassword());
            } else {
                newUser.encodeAndSetPassword(newUser.getPassword());
            }
            userRepository.delete(existingUser.get());
            userRepository.save(newUser);
            return "redirect:/users";
        }
        return "users/edit";

    }

    @GetMapping("create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@Validated @ModelAttribute User newUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            return "users/create";
        }
        Optional<User> existingUser = userRepository.findById(newUser.getId());
        if (existingUser.isPresent()) {
            model.addAttribute("errors", "User exists");
            return "users/index";
        }
        newUser.encodeAndSetPassword(newUser.getPassword());
        userRepository.save(newUser);
        return "redirect:/users";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Users");
        Optional<Role> role = roleRepository.findByRole(Role.USER);
        model.addAttribute("users", userRepository.findAllByRole(role.get()));
        return "users/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] userIds) {
        if (userIds == null) return "redirect:/users/delete";
        for (int id : userIds) {
            userRepository.deleteById(id);
        }
        return "redirect:/users/delete";
    }
}
