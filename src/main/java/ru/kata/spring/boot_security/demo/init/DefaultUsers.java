package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.Set;


@Component
public class DefaultUsers {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public DefaultUsers(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostConstruct
    public void creationOfDefaultUsers() {
        Role role1 = new Role();
        role1.setRoleName("ROLE_ADMIN");
        role1.setId(1);
        roleService.addRole(role1);

        Role role2 = new Role();
        role2.setRoleName("ROLE_USER");
        role2.setId(2);
        roleService.addRole(role2);


        createUser("admin@admin.com", passwordEncoder.encode("admin"), "Admin", "Admin", 99, Set.of(role1, role2));
        createUser("user@user.com", passwordEncoder.encode("user"), "User", "User", 88, Set.of(role2));
    }

    private void createUser(String username, String password, String firstName, String lastName, int age, Set<Role> role) {
        User user = new User(username, password, firstName, lastName, age, role);
        userRepository.save(user);
    }
}