package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.utils.WrapObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductAPIController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/product/add")
    public @ResponseBody
    String addProduct(@RequestBody WrapObject<Product, List<Integer>> requestBody) {
        try {
            productService.addWithCategories(requestBody.getFirstObject(), requestBody.getSecondObject());
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

}