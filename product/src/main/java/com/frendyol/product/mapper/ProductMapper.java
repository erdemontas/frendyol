package com.frendyol.product.mapper;

import com.frendyol.product.dto.ProductRequest;
import com.frendyol.product.dto.ProductResponse;
import com.frendyol.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper map = Mappers.getMapper(ProductMapper.class);

    Product productRequestToProductMapper(ProductRequest productRequest);

    ProductResponse productToProductResponseMapper(Product product);

    List<ProductResponse> productListToProductResponseList(List<Product> productList);
}
