package com.frendyol.product.controller;

import com.frendyol.product.dto.ProductResponse;
import com.frendyol.product.exception.ProductNotFoundException;
import com.frendyol.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    void getProduct_should_return_200_when_product_exists() throws Exception {
        //when

        //given
        given(productService.getProduct(Mockito.anyString())).willReturn(
                new ProductResponse("1","Iphone", 999, "Brand New Phone", "Mobile",new Date().toString()));


        //then

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("name").value("Iphone"))
                .andExpect(jsonPath("price").value(999))
                .andExpect(jsonPath("description").value("Brand New Phone"))
                .andExpect(jsonPath("category").value("Mobile"));

    }


    @Test
    void getProduct_should_return_407_when_product_not_exists() throws Exception {
        //when

        //given
        given(productService.getProduct(Mockito.anyString())).willThrow(new ProductNotFoundException());


        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void listProduct() throws Exception {

    }

    @Test
    void createProduct() {
    }
}