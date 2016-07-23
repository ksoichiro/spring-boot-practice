package com.example.spring.web;

import com.example.spring.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String index(Model model) throws NoSuchFieldException {
        model.addAttribute("list", employeeService.findAll());
        return "index";
    }
}
