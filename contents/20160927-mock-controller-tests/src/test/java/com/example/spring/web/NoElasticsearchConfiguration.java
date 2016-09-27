package com.example.spring.web;

import com.example.spring.App;
import com.example.spring.annotation.ElasticsearchDependentController;
import com.example.spring.annotation.ElasticsearchDependentService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

/**
 * This composite annotation is like {@code SpringBootApplication},
 * but it excludes Elasticsearch auto configuration.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Configuration
@ComponentScan(value = "com.example.spring", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = App.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ElasticsearchDependentService.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ElasticsearchDependentController.class)
})
@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
@EnableJpaRepositories("com.example.spring.repository")
@EntityScan("com.example.spring.domain")
public @interface NoElasticsearchConfiguration {
}
