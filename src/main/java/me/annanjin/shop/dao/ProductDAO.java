package me.annanjin.shop.dao;

import me.annanjin.shop.entity.ProductEntity;

import java.util.List;

public interface ProductDAO extends DAOInterface<Integer, ProductEntity> {
    List<ProductEntity> getByName(String name);

}
