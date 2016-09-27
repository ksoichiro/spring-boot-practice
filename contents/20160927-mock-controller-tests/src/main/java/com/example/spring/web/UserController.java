package com.example.spring.web;

import com.example.spring.repository.UserRepository;
import com.example.spring.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // This service won't be auto-wired when Elasticsearch auto configuration is disabled,
    // so set required to false.
    @Autowired(required = false)
    private LogService logService;

    @RequestMapping
    public String list() {
        return "users/list";
    }
}
