package com.padaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.services", "com.resources", "com.config", "com.exceptions"})
@EnableJpaRepositories(basePackages = {"com.repositories"})
@EntityScan(basePackages = {"com.domains"})
public class PadariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PadariaApplication.class, args);
    }
}