package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;


@Component
public class DefaultUsers {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DefaultUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void creationOfDefaultUsers() {
        var role = new Role("ROLE_ADMIN");
        var role2 = new Role("ROLE_USER");
        roleRepository.saveAll(Set.of(role, role2));
        createUser("admin@admin.com", passwordEncoder.encode("admin"), "Admin", "Admin", 99, Set.of(role, role2));
        createUser("user@user.com", passwordEncoder.encode("user"), "User", "User", 88, Set.of(role2));
    }

    private void createUser(String username, String password, String firstName, String lastName, int age, Set<Role> role) {
        User user = new User(username, password, firstName, lastName, age, role);
        userRepository.save(user);
    }
}