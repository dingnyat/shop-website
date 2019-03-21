package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import me.annanjin.shop.utils.BeanTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private BeanTools beanTools;

    @Override
    public Integer add(Category category) {
        return categoryDAO.add(beanTools.convert(category, new CategoryEntity()));
    }

    @Override
    public void update(Category category) {
        CategoryEntity categoryEntity = categoryDAO.getById(category.getId());
        beanTools.convert(category, categoryEntity);
        categoryDAO.update(categoryEntity);
    }

    @Override
    public void remove(Integer id) {
        CategoryEntity categoryEntity = categoryDAO.getById(id);
        categoryDAO.remove(categoryEntity);
    }

    @Override
    public Category getById(Integer id) {
        CategoryEntity categoryEntity = categoryDAO.getById(id);
        if (categoryEntity == null) return null;
        return beanTools.convert(categoryEntity, new Category());
    }

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> categoryEntities = categoryDAO.getAll();
        if (categoryEntities == null) return null;
        return categoryEntities.stream()
                .map(categoryEntity -> beanTools.convert(categoryEntity, new Category()))
                .collect(Collectors.toList());
    }
}
