package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RoleService {

    Optional<Role> findById(Long id);

    Set<Role> findByRoleIn(List<String> role);

    List<Role> getAllRoles();
}
