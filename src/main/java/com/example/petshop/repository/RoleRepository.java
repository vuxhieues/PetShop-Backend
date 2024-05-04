package com.example.petshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.ERole;
import com.example.petshop.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    
    Optional<Role> findByName(ERole name);

}
