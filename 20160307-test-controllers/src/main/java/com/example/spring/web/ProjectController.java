package com.example.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @RequestMapping
    public String list() {
        return "projects/list";
    }
}
