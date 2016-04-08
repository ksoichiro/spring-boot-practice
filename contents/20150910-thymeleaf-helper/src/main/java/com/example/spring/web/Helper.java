package com.example.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("request")
public class Helper {
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * You can call this method like {@code ${&#064;helper.something()}}.
     */
    public String something() {
        return "Hello! " + httpServletRequest.getHeader("Host");
    }
}
