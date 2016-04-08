package com.example.spring.config;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.AbstractVersionStrategy;

public class PrefixAndFixedVersionStrategy extends AbstractVersionStrategy {
    private final String version;

    public PrefixAndFixedVersionStrategy(String prefix, String version) {
        super(new MiddleVersionPathStrategy(prefix, version));
        this.version = version;
    }

    @Override
    public String getResourceVersion(Resource resource) {
        return this.version;
    }
}
