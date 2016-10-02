package com.example.spring.web;

import com.example.spring.annotation.ElasticsearchDependentService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

// With @ContextConfiguration, the internal static @Configuration classes will be automatically registered.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Configuration
@ComponentScan(value = "com.example.spring", excludeFilters = {
    // To avoid registration of Application class in src/main
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = SpringBootApplication.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ElasticsearchDependentService.class),
    // With this, you can avoid many Bean registrations, but it's often difficult
    // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
@EnableJpaRepositories("com.example.spring.repository")
@EntityScan("com.example.spring.domain")
public @interface StandaloneControllerConfiguration {
}
