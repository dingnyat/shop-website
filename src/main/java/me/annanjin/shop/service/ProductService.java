package me.annanjin.shop.service;

import me.annanjin.shop.model.Product;
import me.annanjin.shop.model.ProductFilterRequest;

import java.util.List;

public interface ProductService extends ServiceInterface<Integer, Product> {
    List<Product> getProductsByCategoryWithFilter(ProductFilterRequest request);

    Long getRecordsTotal(ProductFilterRequest request);
}
