package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/addProduct";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        int productId = productService.add(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model){
        Product product = productService.getById(id);
        model.addAttribute("productEdit",product);
        return "admin/product/editProduct";
    }
    @PostMapping("/product/{id}/edit")
    public String editProduct(@ModelAttribute("productEdit") Product product){
        productService.edit(product);
        return "redirect:/product/list";
    }


    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id, Product product){
        product = productService.getById(id);
        productService.delete(product);
        return "redirect:/product/list";
    }
    @GetMapping("/product/list")
    public String listProduct(Model model) {
        List<Product> listProducts = productService.getAll();
        model.addAttribute("products", listProducts);
        return "admin/product/listProduct";
    }

    @GetMapping("/product/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        Product product = productService.getByName(name);
        model.addAttribute("products", product);
        return "admin/product/listProduct";
    }
}
