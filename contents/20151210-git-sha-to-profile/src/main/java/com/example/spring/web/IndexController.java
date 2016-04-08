package com.example.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @Value("${application.revision}")
    private String revision;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("revision", revision);
        return "index";
    }
}
