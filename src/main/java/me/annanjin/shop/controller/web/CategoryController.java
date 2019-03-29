package me.annanjin.shop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @GetMapping(value = "/category")
    public String view() {
        return "admin/category/category";
    }
}
