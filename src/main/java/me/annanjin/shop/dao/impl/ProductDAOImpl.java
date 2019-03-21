package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl extends DAOAbstract<Integer,ProductEntity> implements ProductDAO{
    @Override
    public List<ProductEntity> getByName(String name) {
        String hql = "select p from ProductEntity p where p.name = :name";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
