package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AdminAccount {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminAccount(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void admin() {
        User admin = new User("admin", 22, "admin@admin", "$2y$10$Q/ByAu6ZatO2e1yWxA2u3OONg6dQwnwNWmDm.bc70Je5/3ti5V6.y");

        Role roleAdmin = new Role("ROLE_ADMIN");

        roleAdmin.setUserList(new ArrayList<>(Collections.singletonList(admin)));
        if (userService.userByUsername(admin.getName()).isEmpty()) {
            userService.save(admin);

            roleService.saveRole(roleAdmin);
        }
    }
}
