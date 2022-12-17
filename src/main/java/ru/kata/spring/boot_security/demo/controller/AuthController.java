package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator personValidator;



    @Autowired
    public AuthController(UserValidator personValidator) {
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String getFormLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getFormRegistrationPage(@ModelAttribute("person") User user) {
        return "auth/registration";
    }


}