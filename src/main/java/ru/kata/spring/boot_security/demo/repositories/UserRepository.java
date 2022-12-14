package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(value = "User.role", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByEmail(String email);
}
