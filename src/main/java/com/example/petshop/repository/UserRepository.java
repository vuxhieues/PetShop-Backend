package com.example.petshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query("Select u From User u Where u.verificationCode = ?1")
    User findByVerificationCode(String code);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
