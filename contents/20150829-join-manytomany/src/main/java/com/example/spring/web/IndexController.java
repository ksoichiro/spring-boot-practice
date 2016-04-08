package com.example.spring.web;

import com.example.spring.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("permissionList", permissionService.findAllByRoleId(2));
        return "index";
    }
}
