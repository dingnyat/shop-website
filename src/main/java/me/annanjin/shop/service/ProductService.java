package me.annanjin.shop.service;

import me.annanjin.shop.model.Product;

import java.util.List;

public interface ProductService extends ServiceInterface<Integer, Product> {
    List<Product> getByName(String name);

    Integer addWithCategories(Product product, List<Integer> categoriyIds);
}
