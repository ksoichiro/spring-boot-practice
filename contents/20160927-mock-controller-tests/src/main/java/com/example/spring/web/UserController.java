package com.example.spring.web;

import com.example.spring.annotation.ElasticsearchDependentController;
import com.example.spring.repository.UserRepository;
import com.example.spring.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@ElasticsearchDependentController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogService logService;

    @RequestMapping
    public String list() {
        return "users/list";
    }
}
