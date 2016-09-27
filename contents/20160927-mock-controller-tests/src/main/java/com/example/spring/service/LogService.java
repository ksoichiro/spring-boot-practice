package com.example.spring.service;

import com.example.spring.annotation.ElasticsearchDependentService;
import com.example.spring.repository.elasticsearch.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

@ElasticsearchDependentService
public class LogService {
    @Autowired
    private LogRepository logRepository;
}
