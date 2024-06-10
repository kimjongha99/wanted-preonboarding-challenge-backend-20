package com.example.wantedchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class WantedChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WantedChallengeApplication.class, args);
    }

}
