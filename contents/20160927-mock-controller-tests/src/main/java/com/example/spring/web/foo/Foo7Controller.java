package com.example.spring.web.foo;

import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foo7")
public class Foo7Controller {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping
    public String list() {
        return "users/list";
    }
}
