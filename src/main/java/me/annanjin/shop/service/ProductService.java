package me.annanjin.shop.service;

import me.annanjin.shop.model.Product;

import java.util.List;

public interface ProductService {
    int add(Product product);

    void edit(Product product);

    void delete(Product product);

    Product getById(int id);

    Product getByName(String name);

    List<Product> getAll();
}
