package com.frendyol.product.service;

import com.frendyol.product.dto.ProductRequest;
import com.frendyol.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(String id);

    List<ProductResponse> listProducts();

    ProductResponse saveProduct(ProductRequest productRequest);
}
