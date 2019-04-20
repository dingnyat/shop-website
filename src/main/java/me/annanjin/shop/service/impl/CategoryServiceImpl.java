package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.util.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceAbstract<Integer, Category, CategoryEntity, CategoryDAO> implements CategoryService {

    public CategoryServiceImpl(@Autowired CategoryDAO repository, @Autowired BeanTools beanTools) {
        super(repository, beanTools);
    }

    @Override
    public Category getByCode(String code) {
        CategoryEntity categoryEntity = repository.getByCode(code);
        if (categoryEntity == null) return null;
        return beanTools.map(categoryEntity, new Category());
    }
}
