package me.annanjin.shop.service;

import me.annanjin.shop.model.Category;
import me.annanjin.shop.model.Product;

import java.util.List;

public interface ProductService extends ServiceInterface<Integer, Product> {
    Integer addWithCategories(Product product, List<Integer> categoriyIds);

    List<Category> getCategoriesOfProduct(Integer id);
}
