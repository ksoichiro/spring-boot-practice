package com.example.spring.web;

import com.example.spring.entity.Account;
import com.example.spring.form.AccountSearchForm;
import com.example.spring.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @ModelAttribute
    public AccountSearchForm accountSearchForm() {
        return new AccountSearchForm();
    }

    @RequestMapping
    public String index() {
        return "forward:/accounts";
    }

    @RequestMapping("/accounts")
    public String list(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "accounts/list";
    }
}
