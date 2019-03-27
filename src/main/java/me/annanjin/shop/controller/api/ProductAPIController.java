package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Category;
import me.annanjin.shop.model.Product;
import me.annanjin.shop.model.TableRecordResponseData;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.utils.WrapObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/admin/product/list")
    public @ResponseBody
    TableRecordResponseData<WrapObject<Product, List<Category>>> products() {
        List<Product> products = productService.getAll();
        List<WrapObject<Product, List<Category>>> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(new WrapObject<>(product, productService.getCategoriesOfProduct(product.getId())));
        }
        return new TableRecordResponseData<>(productList);
    }

}