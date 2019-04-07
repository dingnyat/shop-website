package me.annanjin.shop.controller.api;

import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPIController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/api/category/list")
    public List<Category> categories() {
        return categoryService.getAllRecords();
    }

    @PostMapping("/admin/category/add")
    public String addCategory(@RequestBody Category category) {
        try {
            categoryService.create(category);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @PostMapping("/admin/category/edit")
    String editCategory(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return "Successfully";
        } catch (Exception e) {
            return "Failed!";
        }
    }

    @GetMapping("/admin/category/{id}")
    Category getCategory(@PathVariable("id") int id) {
        return categoryService.getById(id);
    }

    @GetMapping("/admin/category/delete/{id}")
    String deleteCategory(@PathVariable("id") int id) {
        try {
            categoryService.delete(id);
            return "Successfully!";
        } catch (Exception e) {
            return "Failed!";
        }
    }
}
