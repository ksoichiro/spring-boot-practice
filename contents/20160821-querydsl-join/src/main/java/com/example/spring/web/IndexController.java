package com.example.spring.web;

import com.example.spring.service.DepartmentService;
import com.example.spring.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/")
    public String index(Model model, @PageableDefault(size = 3) Pageable pageable) throws NoSuchFieldException {
        model.addAttribute("list", employeeService.findAll(pageable));
        return "index";
    }

    @RequestMapping("/department")
    public String department(Model model, @PageableDefault(size = 3) Pageable pageable) throws NoSuchFieldException {
        model.addAttribute("list", departmentService.findAll(pageable));
        return "department";
    }
}
