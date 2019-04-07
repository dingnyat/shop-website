package me.annanjin.shop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("/")
    public String index() {
        return "client/index";
    }

    @GetMapping("/login")
    public String login() {
        return "client/login";
    }

}
