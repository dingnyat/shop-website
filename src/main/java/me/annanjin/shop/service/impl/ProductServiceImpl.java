package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends ServiceAbstract<Integer, Product, ProductEntity, ProductDAO> implements ProductService {

    public ProductServiceImpl(@Autowired ProductDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }

}

