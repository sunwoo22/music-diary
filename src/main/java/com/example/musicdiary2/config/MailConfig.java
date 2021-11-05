package com.example.musicdiary2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Configuration
public class MailConfig {

//    static Logger logger = LogManager.getLogger(MailConfig.class);

//    public MailConfig() throws IOException {
//        MailConfig.logger.info("MailConfig.java constructor called");
//    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.debug", "true");

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("coanimal547@gmail.com");
        mailSender.setPassword("위 메일의 비밀번호를 입력");
        mailSender.setDefaultEncoding("utf-8");
        return mailSender;
    }

}
