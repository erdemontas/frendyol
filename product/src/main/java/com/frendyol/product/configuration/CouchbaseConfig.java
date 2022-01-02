package com.frendyol.product.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories(basePackages = "com.frendyol.product.repository")
public class CouchbaseConfig  extends AbstractCouchbaseConfiguration {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "frendyol-db-admin";
    }

    @Override
    public String getPassword() {
        return "frend@YOL123";
    }

    @Override
    public String getBucketName() {
        return "product";
    }
}
