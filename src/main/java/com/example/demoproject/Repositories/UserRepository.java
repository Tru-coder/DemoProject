package com.example.demoproject.Repositories;

import com.example.demoproject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String Username);
    User findByActivationCode(String activationCode);

    boolean existsById(Long id);
    void deleteById(Long id);
    void deleteAll();

    List<User> findAllBy();

}
