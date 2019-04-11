package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.Account;
import me.annanjin.shop.model.AccountVerificationToken;
import me.annanjin.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ClientController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "client/index";
    }

    @GetMapping("/login")
    public String login() {
        return "client/login";
    }

    @GetMapping("/verify")
    public String accountVerify(@RequestParam("token") String token, Model model) {
        AccountVerificationToken verificationToken = accountService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("error", true);
            return "client/verify";
        }
        long tokenTime = verificationToken.getTime();
        Date date = new Date();
        if (date.getTime() - tokenTime > 1000 * 60 * 30) { // 30 minutes
            model.addAttribute("error", true);
            return "client/verify";
        }
        Account account = accountService.getById(verificationToken.getAccount().getId());
        account.setEnabled(true);
        accountService.update(account);
        model.addAttribute("success", true);
        model.addAttribute("message", "Error! Token is invalid. You should log in to your account and re-send register confirmation token!");
        return "client/verify";
    }

    @GetMapping("/register-successfully")
    public String registerSuccessfully() {
        return "client/register-successfully";
    }
}
