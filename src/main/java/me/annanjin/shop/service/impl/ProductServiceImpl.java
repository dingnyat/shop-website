package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.dao.ProductCategoryDAO;
import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.entity.ProductCategoryEntity;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.model.Product;
import me.annanjin.shop.service.ProductService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl extends ServiceAbstract<Integer, Product, ProductEntity, ProductDAO> implements ProductService {

    public ProductServiceImpl(@Autowired ProductDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public List<Product> getByName(String name) {
        List<ProductEntity> productEntities = repository.getByName(name);
        return productEntities.stream()
                .map(productEntity -> beanTools.convert(productEntity, new Product()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer addWithCategories(Product product, List<Integer> categoriyIds) {
        Integer id = this.add(product);
        for (Integer categoryId : categoriyIds) {
            ProductCategoryEntity entity = new ProductCategoryEntity();
            entity.setCategoryEntity(categoryDAO.getById(categoryId));
            entity.setProductEntity(repository.getById(id));
            productCategoryDAO.add(entity);
        }
        return id;
    }

    @Override
    public List<Category> getCategoriesOfProduct(Integer id) {
        List<CategoryEntity> categoryEntities = productCategoryDAO.getByProductId(id);
        return categoryEntities.stream()
                .map(categoryEntity -> beanTools.convert(categoryEntity, new Category()))
                .collect(Collectors.toList());
    }
}

