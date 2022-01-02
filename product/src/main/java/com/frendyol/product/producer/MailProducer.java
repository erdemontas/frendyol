package com.frendyol.product.producer;

import org.springframework.kafka.core.KafkaTemplate;

public class MailProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MailProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
