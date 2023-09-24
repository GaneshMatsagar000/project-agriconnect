package com.agri.agriculture;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.agri.agriculture")
@EnableJpaRepositories(basePackages = "com.agri.agriculture.repository")
@EntityScan(basePackages = "com.agri.agriculture.entity")
@Import(SecurityConfig.class)
public class AgricultureApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgricultureApplication.class, args);
    }
}
