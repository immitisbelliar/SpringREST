package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class RegService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        Role role = new Role("ROLE_USER");
        user.setRoleList(new ArrayList<>(Collections.singletonList(role)));
        role.setUserList(new ArrayList<>(Collections.singletonList(user)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        roleRepository.save(role);
    }
}
