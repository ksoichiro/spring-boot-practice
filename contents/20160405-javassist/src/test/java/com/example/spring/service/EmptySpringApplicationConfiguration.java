package com.example.spring.service;

import org.springframework.boot.test.SpringApplicationConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to launch Spring boot without any auto-configuration for tests.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringApplicationConfiguration(EmptyContextConfiguration.class)
public @interface EmptySpringApplicationConfiguration {
}
