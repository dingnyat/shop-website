package me.annanjin.shop.dao;

import me.annanjin.shop.entity.CategoryEntity;

public interface CategoryDAO extends DAOInterface<Integer, CategoryEntity> {
    CategoryEntity getByCode(String code);
}
