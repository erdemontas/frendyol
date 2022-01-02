package com.frendyol.product.controller;

import com.frendyol.product.dto.ProductRequest;
import com.frendyol.product.dto.ProductResponse;
import com.frendyol.product.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    // TODO: Should I inject Interface or Implementation
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //Get Product
    @GetMapping("/{productId}")
    ResponseEntity<ProductResponse> getProduct(@PathVariable String productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<ProductResponse>> listProduct() {
        return new ResponseEntity<>(productService.listProducts(), HttpStatus.OK);
    }

    //Create Product
    @PostMapping()
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }


}

