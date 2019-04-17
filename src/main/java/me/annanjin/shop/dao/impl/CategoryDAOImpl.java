package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class CategoryDAOImpl extends DAOAbstract<Integer, CategoryEntity> implements CategoryDAO {
    @Override
    public CategoryEntity getByCode(String code) {
        try {
            return this.entityManager
                    .createQuery("SELECT c FROM CategoryEntity c WHERE c.code = :code", CategoryEntity.class)
                    .setParameter("code", code).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
