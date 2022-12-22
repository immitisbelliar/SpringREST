package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.getRoles().forEach(x -> x.getRole());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User showUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void createUser(User user, String[] rol) {
        Set<Role> byRoleIn = roleRepository.findByRoleIn(Arrays.asList(rol));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(byRoleIn);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, User updatedUser, String[] rol) {
        var byId = userRepository.findById(id);
        Set<Role> byRoleIn = roleRepository.findByRoleIn(Arrays.asList(rol));
        byId.ifPresent(x -> {
            x.setUsername(updatedUser.getUsername());
            x.setAge(updatedUser.getAge());
            x.setFirstName(updatedUser.getFirstName());
            x.setLastName(updatedUser.getLastName());
            x.setRoles(byRoleIn);
            x.setUsername(updatedUser.getUsername());
            x.setPassword(updatedUser.getPassword());
        });
    }
}