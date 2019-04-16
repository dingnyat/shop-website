package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.CartItemDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.CartItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartItemDAOImpl extends DAOAbstract<Integer, CartItemEntity> implements CartItemDAO {
}
