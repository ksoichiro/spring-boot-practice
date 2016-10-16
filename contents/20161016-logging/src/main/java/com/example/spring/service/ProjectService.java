package com.example.spring.service;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    public void create(String name) {
        if ("bar".equals(name)) {
            throw new IllegalStateException("Service throws exception");
        }
    }
}
