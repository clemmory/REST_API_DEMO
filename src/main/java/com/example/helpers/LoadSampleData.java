package com.example.helpers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Presentation;
import com.example.entities.Product;
import com.example.services.PresentationService;
import com.example.services.ProductService;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(ProductService productService, PresentationService presentationService){

        return data -> {

            presentationService.save(Presentation.builder()
                .name("unidad")
                .build());
            presentationService.save(Presentation.builder()
                .name("docena")
                .build());

            productService.save(Product.builder()
                .name("Apple TV")
                .description("latest apple tv")
                .stock(23)
                .price(12890)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Iphone 15")
                .description("nuevo iphone")
                .stock(1250)
                .price(1500)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Dress")
                .description("Zara black dress")
                .stock(100)
                .price(45.5)
                .presentation(presentationService.findById(2))
                .build());

            productService.save(Product.builder()
                .name("Mouse")
                .description("Computer bluetooth mouse")
                .stock(3)
                .price(56)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Desk Cahir")
                .description("desk chair on wheels")
                .stock(15)
                .price(99.99)
                .presentation(presentationService.findById(2))
                .build());

            productService.save(Product.builder()
                .name("Desk")
                .description("Wooden design desk")
                .stock(23)
                .price(459)
                .presentation(presentationService.findById(2))
                .build());

            productService.save(Product.builder()
                .name("Apple")
                .description("Apple Grani pack of 10Kg")
                .stock(100)
                .price(5.67)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Nike")
                .description("nike trainers white and blue")
                .stock(98)
                .price(98)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Ring")
                .description("Golden ring with diamond")
                .stock(234)
                .price(3456)
                .presentation(presentationService.findById(2))
                .build());

            productService.save(Product.builder()
                .name("Computer HP")
                .description("Computer HP black")
                .stock(876)
                .price(789)
                .presentation(presentationService.findById(1))
                .build());

            productService.save(Product.builder()
                .name("Handbag")
                .description("Woman handbag Chanel")
                .stock(87)
                .price(455)
                .presentation(presentationService.findById(1))
                .build());

            
        };
    }

}
