package com.example.spring.web;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;

public class DefaultStandaloneControllerConfig {
    // Without @WebAppConfiguration and @SpringBootApplication, this definition is required
    @Bean
    ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }
}
