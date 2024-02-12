package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Presentation;

@Repository
public interface PresentationDao extends JpaRepository<Presentation, Integer> {

}
