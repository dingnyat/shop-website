package me.annanjin.shop.controller.web;

import me.annanjin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/add")
    public String addProduct() {
        return "admin/product/add-product";
    }
}
