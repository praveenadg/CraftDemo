package com.mycompany.craftdemo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.mycompany.craftdemo"})
@EnableJpaRepositories("com.mycompany.craftdemo.dao")
@EntityScan("com.mycompany.craftdemo.model")
public class CraftDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftDemoApplication.class, args);
    }
}
