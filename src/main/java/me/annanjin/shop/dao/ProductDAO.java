package me.annanjin.shop.dao;

import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.ProductFilterRequest;

import java.util.List;

public interface ProductDAO extends DAOInterface<Integer, ProductEntity> {
    List<ProductEntity> getProductsByCategoryWithFilter(ProductFilterRequest request);

    Long getFilteredRecordsTotal(ProductFilterRequest request);

    ProductEntity getByCode(String code);
}
