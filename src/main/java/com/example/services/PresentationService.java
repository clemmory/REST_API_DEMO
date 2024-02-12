package com.example.services;

import java.util.List;

import com.example.entities.Presentation;

public interface PresentationService {

    public List<Presentation> findAll();
    public Presentation findById(int id);
    public void save (Presentation presentation);
    public void delete(Presentation presentation);

}
