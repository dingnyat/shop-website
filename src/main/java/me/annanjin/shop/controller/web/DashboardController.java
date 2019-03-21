package me.annanjin.shop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping(value = {"/admin/dashboard", "/admin"})
    public String dashboard() {
        return "admin/dashboard";
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
