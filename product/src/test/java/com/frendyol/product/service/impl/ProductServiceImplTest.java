package com.frendyol.product.service.impl;


import com.frendyol.product.dto.ProductRequest;
import com.frendyol.product.dto.ProductResponse;
import com.frendyol.product.entity.Product;
import com.frendyol.product.exception.ProductNotFoundException;
import com.frendyol.product.repository.ProductRepository;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    private Product product;
    private ProductRequest productRequest;
    private List<Product> productList;

    @BeforeEach
    public void init(){
        product = new Product("1", "Iphone", 999.90, "Brand New Phone", "Mobile");
        productRequest = new ProductRequest("Iphone",999.90, "Brand new Phone", "Mobile");
        productList = new ArrayList<Product>();
        productList.add(product);
    }

    @Test
    void getProduct_should_return_productResponse() {
        when(productRepository.findById("1")).thenReturn(java.util.Optional.of(product));
        ProductResponse productResponse = productService.getProduct("1");

        assertNotNull(productResponse);
        assertEquals("Mobile", productResponse.getCategory());
    }

    @Test
    void getProduct_should_throw_ProductNotFoundException_when_product_not_found() {
        when(productRepository.findById("1")).thenThrow(new ProductNotFoundException());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProduct("1");
        });
    }

    @Test
    void saveProduct_should_create_new_product_when_product_given() {
        when(productRepository.save(any())).thenReturn(product);


        ProductResponse response = productService.saveProduct(productRequest);
        assertEquals(response.getName(), "Iphone");
    }


    @Test
    void listProducts_should_return_ProductResponse_list() {
        when(productRepository.findAll()).thenReturn(productList);
        List<ProductResponse> response = productService.listProducts();
        assertEquals(1, response.size());
        assertEquals("1", response.get(0).getId());
    }

    @Test
    void listProducts_should_return_when_no_product() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        List<ProductResponse> response = productService.listProducts();
        assertEquals(0, response.size());
    }
}