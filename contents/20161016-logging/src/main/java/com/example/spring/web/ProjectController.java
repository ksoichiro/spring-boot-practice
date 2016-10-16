package com.example.spring.web;

import com.example.spring.form.ProjectCreateForm;
import com.example.spring.service.ProjectService;
import com.example.spring.util.LogHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private LogHandler logHandler;

    @ModelAttribute
    public ProjectCreateForm projectCreateForm() {
        return new ProjectCreateForm();
    }

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/projects")
    public String list() {
        return "projects/list";
    }

    @RequestMapping("/projects/ex")
    public String listWithException() {
        throw new RuntimeException("This is a test exception");
    }

    @RequestMapping("/projects/create")
    public String create(@Validated ProjectCreateForm projectCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("message.error.param", new Object[]{"foo"}, null);
            return "projects/list";
        }
        try {
            projectService.create(projectCreateForm.getName());
        } catch (Exception e) {
            logHandler.handle(e);
        }
        return "projects/list";
    }

    @RequestMapping("/projects/create/ex")
    public String createWithException(@Validated ProjectCreateForm projectCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.reject("message.error.param", new Object[]{"foo"}, null);
            throw new RuntimeException("This is a test exception");
        }
        throw new RuntimeException("This is a test exception");
    }
}
