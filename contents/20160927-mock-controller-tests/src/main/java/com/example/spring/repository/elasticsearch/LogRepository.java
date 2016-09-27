package com.example.spring.repository.elasticsearch;

import com.example.spring.domain.elasticsearch.Log;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<Log, Integer> {
}
