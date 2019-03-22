package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.ProductCategoryDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.entity.ProductCategoryEntity;
import me.annanjin.shop.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ProductCategoryDAOImpl extends DAOAbstract<Integer, ProductCategoryEntity> implements ProductCategoryDAO {
    @Override
    public List<ProductEntity> getByCategoryId(Integer id) {
        String hql = "select a.productEntity from ProductCategoryEntity a where a.categoryEntity.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<CategoryEntity> getByProductId(Integer id) {
        String hql = "select a.categoryEntity from ProductCategoryEntity a where a.productEntity.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
