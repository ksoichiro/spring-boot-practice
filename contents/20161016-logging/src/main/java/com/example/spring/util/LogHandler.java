package com.example.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogHandler {
    public static String CAUGHT_ERROR_ATTRIBUTE_NAME = "error";

    @Autowired
    private HttpServletRequest request;

    public void handle(Throwable e) {
        request.setAttribute(CAUGHT_ERROR_ATTRIBUTE_NAME, e);
    }
}
