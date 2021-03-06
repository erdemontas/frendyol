package com.frendyol.product.entity;


import lombok.*;
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
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int stock;

    @Temporal(TemporalType.DATE)
    private final Date created_at = new Date();
}
