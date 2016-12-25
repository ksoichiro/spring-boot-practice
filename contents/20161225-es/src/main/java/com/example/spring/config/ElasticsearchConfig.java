package com.example.spring.config;

import com.example.spring.entity.Account;
import com.example.spring.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@Slf4j
public class ElasticsearchConfig {
    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() throws IOException {
        log.info("Start loading accounts");
        ClasspathResourceLoader loader = new ClasspathResourceLoader();
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader objectReader = mapper.readerFor(Account.class);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(loader.openResource("import.json")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Account account = objectReader.readValue(line);
                accountRepository.save(account);
            }
        }
        log.info("Finished loading");
    }
}
