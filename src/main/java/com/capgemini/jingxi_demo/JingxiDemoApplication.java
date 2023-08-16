package com.capgemini.jingxi_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JingxiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JingxiDemoApplication.class, args);
    }

}
