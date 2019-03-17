package me.annanjin.shop.controller.web;

import me.annanjin.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/login")
    public String login() {
        /*Account account1 = new Account("admin", "123456", "dingnyat", "taiping", "0987654321", "email@email.com", "123.jpg");
        Account account2 = new Account("testuser", "123456", "dingnyat", "taiping", "0987654322", "email2@email.com", "123.jpg");
        accountService.add(account1);
        accountService.add(account2);*/
        return "login";
    }
}
