package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public int add(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setProductFileName(product.getProductFileName());
        return productDAO.add(productEntity);
    }

    @Override
    public void edit(Product product) {
        ProductEntity productEntity = productDAO.getById(product.getId());
        productEntity.setName(product.getName());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setProductFileName(product.getProductFileName());
        productDAO.update(productEntity);
    }

    @Override
    public void delete(Product product) {
        ProductEntity productEntity = productDAO.getById(product.getId());
        productDAO.remove(productEntity);
    }

    @Override
    public Product getById(int id) {
        ProductEntity productEntity = productDAO.getById(id);
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setQuantity(productEntity.getQuantity());
        product.setPrice(productEntity.getPrice());
        product.setDescription(productEntity.getDescription());
        product.setProductFileName(productEntity.getProductFileName());
        return product;
    }

    @Override
    public List<Product> getByName(String name) {
        List<ProductEntity> productEntities = productDAO.getByName(name);
        List<Product> listProduct = new ArrayList<Product>();
        for(ProductEntity productEntity : productEntities){
            Product product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setQuantity(productEntity.getQuantity());
            product.setPrice(productEntity.getPrice());
            product.setDescription(productEntity.getDescription());
            product.setProductFileName(productEntity.getProductFileName());
            listProduct.add(product);
        }
        return listProduct;
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = productDAO.getAll();
        List<Product> listProduct = new ArrayList<Product>();
        for (ProductEntity productEntity : productEntities) {
            Product product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setQuantity(productEntity.getQuantity());
            product.setPrice(productEntity.getPrice());
            product.setDescription(productEntity.getDescription());
            product.setProductFileName(productEntity.getProductFileName());
            listProduct.add(product);
        }
        return listProduct;
    }
}

