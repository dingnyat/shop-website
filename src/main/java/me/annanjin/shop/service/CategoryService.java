package me.annanjin.shop.service;

import me.annanjin.shop.model.Category;

import java.util.List;

public interface CategoryService {
    int add(Category category);

    void edit(Category category);

    void delete(Category category);

    Category getById(int id);

    List<Category> getAll();
}
