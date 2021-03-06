package com.example.musicdiary2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MusicDiary2Application {

    public static void main(String[] args) {

        SpringApplication.run(MusicDiary2Application.class, args);

    }

}
