package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project2.models.Role;
import project2.models.User;
import project2.service.RoleService;
import project2.service.UserService;

import java.util.Optional;


@Controller
@RequestMapping("users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String displayAllUsers(Model model) {
        Optional<Role> role = roleService.findByRole(Role.USER);
        model.addAttribute("users", userService.findAllByRole(role.get()));
        model.addAttribute("title", "All Users");

        return "users/index";
    }

    @GetMapping("edit")
    public String displayEditUserForm(@RequestParam() String username, Model model) {
        Optional<User> result = userService.findByUsername(username);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Username: " + username);
        } else {
            User user = result.get();
            model.addAttribute("title", user.getUsername()+ " Details");
            model.addAttribute("oldUsername", username);
            User newUser = new User();
            model.addAttribute("user", newUser);

            Iterable<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);
        }
        return "/users/edit";
    }

    @PostMapping("edit")
    public String processEditUserForm(@RequestParam() String username, @RequestParam() String oldUsername, @Validated @ModelAttribute User newUser, Model model) {
        Optional<User> existingUser = userService.findByUsername(oldUsername);
        System.out.println(oldUsername);
        System.out.println(existingUser.get().getUsername());
        if (existingUser.isPresent()) {
            userService.editUser(oldUsername, newUser, existingUser.get());
            return "redirect:/users";
        }
        return "users/edit";
    }

    @GetMapping("create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("create")
    public String processCreateUserForm(@Validated @ModelAttribute User newUser, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            return "users/create";
        }
        Optional<User> existingUser = userService.findById(newUser.getId());
        if (existingUser.isPresent()) {
            model.addAttribute("errors", "User exists");
            return "users/index";
        }
        newUser.encodeAndSetPassword(newUser.getPassword());
        userService.save(newUser);
        return "redirect:/users";
    }

    @GetMapping("delete")
    public String displayDeleteUsersForm(Model model) {
        model.addAttribute("title", "Delete Users");
        Optional<Role> role = roleService.findByRole(Role.USER);
        model.addAttribute("users", userService.findAllByRole(role.get()));
        return "users/delete";
    }

    @PostMapping("delete")
    public String processDeleteUsersForm(@RequestParam(required = false) int[] userIds) {
        if (userIds == null) return "redirect:/users/delete";
        for (int id : userIds) {
            userService.deleteById(id);
        }
        return "redirect:/users/delete";
    }
}
