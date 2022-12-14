package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidation;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RegService regService;
    private final UserValidation userValidation;

    @Autowired
    public AdminController(UserService userService, RegService regService, UserValidation userValidation) {
        this.userService = userService;
        this.regService = regService;
        this.userValidation = userValidation;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "admin";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "newperson";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidation.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "newperson";
        }
        regService.register(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.showUser(id));
        return "/editperson";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable int id) {
        userValidation.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "editperson";
        }
        userService.update(id, user);
        return "redirect:/admin";
    }
}
