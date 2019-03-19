package me.annanjin.shop.controller.web;

import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    public String listCategory(){
//        Category category = new Category();
//        category = categoryService.getById(1);
//        System.out.println(category.getId());
//        System.out.println(category.getCode());
//        System.out.println(category.getName());

        List<Category> categories = categoryService.getAll();
        int i = 0;
        for(Category c : categories){
            System.out.println(i++);
            System.out.println(c.getId());
            System.out.println(c.getCode());
            System.out.println(c.getName());
        }
        return "admin/category/listCategory";
    }
}
