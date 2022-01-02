package com.frendyol.mailservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerConsumer.class);


    @KafkaListener(
            concurrency = "2",
            topics = "${spring.kafka.consumer.topic-name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void mailListener(String content){
        LOGGER.info("Email detected: "+ content);

    }
}
