package com.example.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private GitProperties gitProperties;

    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        VersionResourceResolver versionResolver = new VersionResourceResolver()
            .addContentVersionStrategy("/css/**", "/js/**")
            .addVersionStrategy(new PrefixAndFixedVersionStrategy("lib/", gitProperties.getCommitId()), "/lib/**");
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:static/")
            .setCachePeriod(null)
            .resourceChain(true)
            .addResolver(versionResolver);
    }
}
