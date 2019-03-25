package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryAPIController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category/list")
    public @ResponseBody
    List<Category> categories() {
        return categoryService.getAll();
    }
}
