package com.example.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entities.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByRole(String role);

}
