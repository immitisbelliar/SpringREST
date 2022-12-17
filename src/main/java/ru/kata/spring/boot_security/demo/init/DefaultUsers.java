//package ru.kata.spring.boot_security.demo.init;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.repository.UserRepository;
//import ru.kata.spring.boot_security.demo.repository.RoleRepository;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
//
//@Component
//public class DefaultUsers {
//
//    private final UserRepository userRepository;
//
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public DefaultUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    @PostConstruct
//    public void creationOfDefaultUsers() {
//        var role = new Role("ROLE_ADMIN");
//        var role2 = new Role("ROLE_USER");
//        roleRepository.saveAll(Set.of(role, role2));
//        saveUserInDataBase("admin", 2000, passwordEncoder.encode("admin"), Set.of(role));
//        saveUserInDataBase("user", 2000, passwordEncoder.encode("user"), Set.of(role2));
//    }
//
//    @Transactional
//    public void saveUserInDataBase(String name, int age, String password, Set<Role> role) {
//        User user = new User(name, age, password, role);
//        userRepository.save(user);
//    }
//}