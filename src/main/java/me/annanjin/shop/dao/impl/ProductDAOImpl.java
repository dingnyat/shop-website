package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDAOImpl extends DAOAbstract<Integer, ProductEntity> implements ProductDAO {
}
