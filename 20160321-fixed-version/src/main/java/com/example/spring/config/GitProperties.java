package com.example.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/git.properties")
public class GitProperties {
    @Autowired
    private Environment environment;

    public String getCommitId() {
        return environment.getProperty("git.commit.id");
    }
}
