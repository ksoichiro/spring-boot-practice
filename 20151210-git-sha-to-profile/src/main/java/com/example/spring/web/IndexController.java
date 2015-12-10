package com.example.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @Value("${application.version}")
    private String version;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("version", version);
        return "index";
    }
}
