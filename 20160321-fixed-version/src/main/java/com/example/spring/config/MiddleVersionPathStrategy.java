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
        return this.prefix + requestPath.substring(this.prefix.length() + this.version.length());
    }

    @Override
    public String addVersion(String path, String version) {
        log.info("addVersion: {}", path);
        if (path.startsWith(".")) {
            return path;
        } else {
            String p = path;
            if (p.startsWith("/")) {
                p = p.substring(1);
            }
            if (p.startsWith(this.prefix)) {
                return this.prefix + this.version + "/" + p.substring(this.prefix.length());
            } else {
                return path;
            }
        }
    }
}
