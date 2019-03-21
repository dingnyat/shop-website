package me.annanjin.shop.dao;

import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.entity.ProductCategoryEntity;
import me.annanjin.shop.entity.ProductEntity;

import java.util.List;

public interface ProductCategoryDAO extends DAOInterface<Integer, ProductCategoryEntity> {
    List<ProductEntity> getByCategoryId(Integer id);

    List<CategoryEntity> getByProductId(Integer id);
}
