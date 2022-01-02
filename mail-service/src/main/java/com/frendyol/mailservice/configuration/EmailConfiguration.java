package com.frendyol.mailservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Getter
@Setter
@Configuration
public class EmailConfiguration {

    private String host;
    private int port;

    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender  = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;

    }
}
