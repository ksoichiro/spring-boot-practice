package com.example.spring.domain.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "log")
@Data
public class Log {
    @Id
    private Integer id;

    private String description;
}
