package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import me.annanjin.shop.service.ServiceAbstract;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceAbstract<Integer, Category, CategoryEntity, CategoryDAO> implements CategoryService {
    @Autowired
    public CategoryServiceImpl(CategoryDAO repository, BeanTools beanTools) {
        super(repository, beanTools);
    }
}
