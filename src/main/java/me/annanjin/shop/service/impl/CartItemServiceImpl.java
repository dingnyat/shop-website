package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CartItemDAO;
import me.annanjin.shop.entity.CartItemEntity;
import me.annanjin.shop.model.CartItem;
import me.annanjin.shop.service.CartItemService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartItemServiceImpl extends ServiceAbstract<Integer, CartItem, CartItemEntity, CartItemDAO> implements CartItemService {
    public CartItemServiceImpl(@Autowired CartItemDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }
}
