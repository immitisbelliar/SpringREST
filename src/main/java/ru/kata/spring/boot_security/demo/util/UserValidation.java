package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Optional;

@Component
public class UserValidation implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> userCheckByName =userService.userByUsername(user.getName());
        if (userCheckByName.isPresent()) {
            errors.rejectValue("name", "", "Данное имя пользователя уже занято");
        }
    }
}
