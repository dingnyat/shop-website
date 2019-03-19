package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryDAOImpl extends DAOAbstract<Integer, CategoryEntity> implements CategoryDAO{
}
