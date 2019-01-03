package com.xushuai.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecurityExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityExampleApplication.class, args);
    }

    @GetMapping("desc")
    public String desc() {
        return "This example is security example";
    }
}
