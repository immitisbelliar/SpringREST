package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getViewsLoginPage() {
        return "auth/login";
    }
}