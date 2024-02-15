package com.example.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Product;
import com.example.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    //Method that returned all products in JSON format
    // The method responds to the request: http://localhost:8080/products?page=0&size=3

    @GetMapping
    public ResponseEntity<List<Product>> findAll(
        @RequestParam(name="page", required = false) Integer page,
        @RequestParam(name="size", required = false) Integer size ){

        ResponseEntity <List<Product>> responseEntity = null;

        Sort sortByName = Sort.by("name");
        List<Product> products = new ArrayList<>();

        //check if the page and size have been sent
        if(page != null & size != null){
            //return the paginated products
            Pageable pageable = PageRequest.of(page,size,sortByName);
            Page<Product> pageProducts = productService.findAll(pageable);
            products = pageProducts.getContent();
            responseEntity = new ResponseEntity<List<Product>>(products,HttpStatus.OK);

        }else{
            //Only ordonate
            products = productService.findAll(sortByName);
            responseEntity = new ResponseEntity<List<Product>>(products,HttpStatus.OK);

        }
        
        return responseEntity;
    }

    //Method that saves a product and verify that the product is well conceived

    @PostMapping
    public ResponseEntity<Map<String,Object>> saveProduct(@Valid @RequestBody Product product, BindingResult validationResults) {

        Map<String, Object> responseAsMap = new HashMap<>();
        ResponseEntity <Map<String,Object>> responseEntity = null;

        //Check if the product contains errors
        if(validationResults.hasErrors()){
            List<String> errores = new ArrayList<>();

            List<ObjectError> objectErrors = validationResults.getAllErrors();
            objectErrors.forEach(objectError -> errores.add(objectError.getDefaultMessage()));

            responseAsMap.put("errores", errores);
            responseAsMap.put("Producto Mal Formado", product);

            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

            return responseEntity;

        }
        try {
            Product savedProduct = productService.save(product);
            String successMessage = "El producto se ha persistido exitosamente";
            responseAsMap.put("Sucess Message", successMessage);
            responseAsMap.put("saved product", savedProduct);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            String error = "Error al intentat persistir le producto y la causa mas probable es: " + e.getMostSpecificCause();
            responseAsMap.put("error",error);
            responseAsMap.put("product", product);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }    
            return responseEntity;



    }

    //Method that edit a product according to id received

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateeProduct(@Valid @RequestBody Product product, BindingResult validationResults,
    @PathVariable(name = "id", required = true) Integer idProduct) {

        Map<String, Object> responseAsMap = new HashMap<>();
        ResponseEntity <Map<String,Object>> responseEntity = null;

        //Check if the product contains errors
        if(validationResults.hasErrors()){
            List<String> errores = new ArrayList<>();

            List<ObjectError> objectErrors = validationResults.getAllErrors();
            objectErrors.forEach(objectError -> errores.add(objectError.getDefaultMessage()));

            responseAsMap.put("errores", errores);
            responseAsMap.put("Producto Mal Formado", product);

            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

            return responseEntity;

        }
        try {
            product.setId(idProduct);
            Product savedProduct = productService.save(product);
            String successMessage = "El producto se ha persistido exitosamente";
            responseAsMap.put("Sucess Message", successMessage);
            responseAsMap.put("saved product", savedProduct);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            String error = "Error al intentat persistir le producto y la causa mas probable es: " + e.getMostSpecificCause();
            responseAsMap.put("error",error);
            responseAsMap.put("product", product);
            responseEntity = new ResponseEntity<Map<String,Object>>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }    
            return responseEntity;



    }
   

}
