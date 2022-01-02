package com.frendyol.product.repository;

import com.frendyol.product.entity.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product, String > {

    Product findByName(String name);

}
