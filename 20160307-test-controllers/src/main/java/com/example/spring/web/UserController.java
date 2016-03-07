package com.example.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @RequestMapping
    public String list() {
        return "users/list";
    }
}
