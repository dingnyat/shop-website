package me.annanjin.shop.controller.web;

import me.annanjin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    public ModelAndView listCategory() {
        ModelAndView modelAndView = new ModelAndView("admin/category/listCategory");
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }
}
