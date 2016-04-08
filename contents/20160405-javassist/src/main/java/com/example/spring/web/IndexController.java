package com.example.spring.web;

import com.example.spring.domain.Os;
import com.example.spring.service.OsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @Autowired
    private OsService osService;

    @RequestMapping("/")
    public String index(Model model) throws NoSuchFieldException {
        for (Annotation a : Os.class.getDeclaredField("foo").getAnnotations()) {
            log.info("Annotation of Os.foo: {}", a.annotationType().getCanonicalName());
        }
        model.addAttribute("osList", osService.findAll());
        return "index";
    }
}
