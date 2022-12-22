package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {

        this.userService = userService;

        this.roleService = roleService;
    }


    @GetMapping()
    public String getAdminPageView(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("admin", userService.showUser(user.getId()));
        model.addAttribute("listOfUsers", userService.getAllUsers());
        model.addAttribute("personalRole", user.returnTheSetOfRolesToString(userService.showUser(user.getId()).getRoles()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminViews/adminPage";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping(value = "update/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             @PathVariable("id") int id, @RequestParam(value = "my_roles[]") String[] roles) {
        userService.updateUser(id, user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String getViewForNewUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("admin", userService.showUser(user.getId()));
        model.addAttribute("user", new User());
        model.addAttribute("personalRole", user.returnTheSetOfRolesToString(userService.showUser(user.getId()).getRoles()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminViews/newUser";
    }

    @PostMapping("/newUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "my_roles[]") String[] roles) {
        userService.createUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/personalPage")
    public String getViewsPersonalPageAdmin(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("admin", userService.showUser(user.getId()));
        model.addAttribute("role", user.returnTheSetOfRolesToString(userService.showUser(user.getId()).getRoles()));
        return "adminViews/adminPersonalPage";
    }
}