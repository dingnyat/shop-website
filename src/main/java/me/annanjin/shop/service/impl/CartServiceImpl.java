package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CartDAO;
import me.annanjin.shop.entity.CartEntity;
import me.annanjin.shop.model.Cart;
import me.annanjin.shop.service.CartService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl extends ServiceAbstract<Integer, Cart,CartEntity, CartDAO> implements CartService {
    public CartServiceImpl(@Autowired CartDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }
}
