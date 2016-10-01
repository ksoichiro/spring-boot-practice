package com.example.spring.web;

import com.example.spring.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // Usually Pageable requires WebAppConfiguration in tests
    @RequestMapping
    public String list(Model model, Pageable pageable) {
        model.addAttribute("list", projectService.findAll(pageable));
        return "projects/list";
    }

    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("This is test");
    }
}
