package com.example.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.resource.VersionPathStrategy;

@Slf4j
public class MiddleVersionPathStrategy implements VersionPathStrategy {
    private final String prefix;
    private final String version;

    public MiddleVersionPathStrategy(String prefix, String version) {
        this.prefix = prefix;
        this.version = version;
    }

    @Override
    public String extractVersion(String requestPath) {
        if (requestPath.startsWith(this.prefix)) {
            String prefixRemoved = requestPath.substring(this.prefix.length());
            if (prefixRemoved.startsWith(this.version)) {
                return this.version;
            }
        }
        return null;
    }

    @Override
    public String removeVersion(String requestPath, String version) {
        return requestPath.substring(this.prefix.length()).substring(this.version.length());
    }

    @Override
    public String addVersion(String path, String version) {
        log.info("addVersion: {}", path);
        if (path.startsWith(".")) {
            return path;
        } else {
            return (path.startsWith("/") ? this.prefix + this.version + path : this.prefix + this.version + "/" + path);
        }
    }
}
