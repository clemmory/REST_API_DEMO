package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.PresentationDao;
import com.example.entities.Presentation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService{

    private final PresentationDao presentationDao;

    @Override
    public List<Presentation> findAll() {
        return presentationDao.findAll();
    }

    @Override
    public Presentation findById(int id) {
        return presentationDao.findById(id).get();
    }

    @Override
    public void save(Presentation presentation) {
     presentationDao.save(presentation);
    }

    @Override
    public void delete(Presentation presentation) {
        presentationDao.delete(presentation);
    }

}
