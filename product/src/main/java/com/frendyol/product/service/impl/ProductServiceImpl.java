package com.frendyol.product.service.impl;

import com.frendyol.product.dto.ProductRequest;
import com.frendyol.product.dto.ProductResponse;
import com.frendyol.product.entity.Product;
import com.frendyol.product.exception.ProductNotFoundException;
import com.frendyol.product.mapper.ProductMapper;
import com.frendyol.product.repository.ProductRepository;
import com.frendyol.product.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${spring.kafka.producer.topic-name}")
    private String topicName;

    private final ProductRepository productRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductServiceImpl(ProductRepository productRepository,
                              KafkaTemplate<String, String> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public ProductResponse getProduct(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        ProductResponse productResponse = ProductMapper.map.productToProductResponseMapper(product);

        return productResponse;
    }


    @Override
    public List<ProductResponse> listProducts() {

        List<Product> productList = productRepository.findAll();

        return ProductMapper.map.productListToProductResponseList(productList);
    }


    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {

        Product product = ProductMapper.map.productRequestToProductMapper(productRequest);

        Product savedProduct = productRepository.save(product);

        String notificationMessage = "Product %s added with %s Id at %s. Price is : %s";
        String emailBody = String.format(notificationMessage, savedProduct.getName(), savedProduct.getId().toString(), savedProduct.getCreated_at().toString(), savedProduct.getPrice());

        kafkaTemplate.send(topicName, emailBody);


        return ProductMapper.map.productToProductResponseMapper(savedProduct);
    }


}
