package com.example.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Product;
import com.example.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService ProductService;

    //Method that returned all products in JSON format
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){

        var products = ProductService.findAll();
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
        
        return responseEntity;
    }

}
