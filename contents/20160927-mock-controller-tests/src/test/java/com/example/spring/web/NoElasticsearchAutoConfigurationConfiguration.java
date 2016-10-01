package com.example.spring.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * This composite annotation is like {@code SpringBootApplication},
 * but it excludes Elasticsearch auto configuration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Configuration
@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public @interface NoElasticsearchAutoConfigurationConfiguration {
}
