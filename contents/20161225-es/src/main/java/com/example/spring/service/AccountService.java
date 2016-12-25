package com.example.spring.service;

import com.example.spring.entity.Account;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<Account> findAll() {
        SearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.matchAllQuery())
            .withPageable(new PageRequest(0, Integer.MAX_VALUE))
            .build();
        return elasticsearchTemplate.queryForList(query, Account.class);
    }
}
