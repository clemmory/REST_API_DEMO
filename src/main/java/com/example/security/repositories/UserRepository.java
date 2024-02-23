package com.example.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entities.MyUser;



public interface UserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
