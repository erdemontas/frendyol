package com.frendyol.product.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;



import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    private String name;
    private double price;
    private String description;
    private String category;

    @Temporal(TemporalType.DATE)
    private final Date created_at = new Date();
}
