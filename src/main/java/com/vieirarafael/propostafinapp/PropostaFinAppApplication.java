package com.vieirarafael.propostafinapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropostaFinAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropostaFinAppApplication.class, args);
    }

}
