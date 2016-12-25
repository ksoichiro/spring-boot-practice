package com.example.spring.repository;

import com.example.spring.entity.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccountRepository extends ElasticsearchRepository<Account, Integer> {
}
