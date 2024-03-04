package me.ronygomes.reference.spring_security.repository;

import me.ronygomes.reference.spring_security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
