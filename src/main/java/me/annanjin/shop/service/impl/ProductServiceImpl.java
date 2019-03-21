package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private BeanTools beanTools;

    @Override
    public Integer add(Product product) {
        return productDAO.add(beanTools.convert(product, new ProductEntity()));
    }

    @Override
    public void update(Product product) {
        ProductEntity productEntity = productDAO.getById(product.getId());
        beanTools.convert(product, productEntity);
        productDAO.update(productEntity);
    }

    @Override
    public void remove(Integer id) {
        ProductEntity productEntity = productDAO.getById(id);
        productDAO.remove(productEntity);
    }

    @Override
    public Product getById(Integer id) {
        ProductEntity productEntity = productDAO.getById(id);
        return beanTools.convert(productEntity, new Product());
    }

    @Override
    public List<Product> getByName(String name) {
        List<ProductEntity> productEntities = productDAO.getByName(name);
        return productEntities.stream()
                .map(productEntity -> beanTools.convert(productEntity, new Product()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = productDAO.getAll();
        return productEntities.stream()
                .map(productEntity -> beanTools.convert(productEntity, new Product()))
                .collect(Collectors.toList());
    }
}

