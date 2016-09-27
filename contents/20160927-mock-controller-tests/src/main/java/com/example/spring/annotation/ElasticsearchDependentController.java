package com.example.spring.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Controller
public @interface ElasticsearchDependentController {
}
