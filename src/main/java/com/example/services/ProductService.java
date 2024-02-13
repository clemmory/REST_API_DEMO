package com.example.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.entities.Product;

public interface ProductService {

    public Page<Product> findAll(Pageable pageable);
    public List<Product> findAll(Sort sort);
    public Product findById(int id);
    public Product save(Product product);
    public void delete(Product product);
    public List<Product> findAll();


}
