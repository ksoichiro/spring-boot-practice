package com.example.spring.service;

import com.example.spring.domain.Os;
import com.example.spring.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsService {
    @Autowired
    private OsRepository osRepository;

    public Iterable<Os> findAll() {
        return osRepository.findAll();
    }
}
