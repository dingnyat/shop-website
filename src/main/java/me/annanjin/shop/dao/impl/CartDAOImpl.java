package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.CartDAO;
import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.entity.CartEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartDAOImpl extends DAOAbstract<Integer, CartEntity> implements CartDAO {
}
