package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

@Service
public interface RoleService {
    void saveRole(Role role);
}