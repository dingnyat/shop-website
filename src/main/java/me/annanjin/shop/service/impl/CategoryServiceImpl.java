package me.annanjin.shop.service.impl;

import me.annanjin.shop.dao.CategoryDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public int add(Category category) {
        return 0;
    }

    @Override
    public void edit(Category category) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Category getById(int id) {
        CategoryEntity categoryEntity = categoryDAO.getById(id);
        if(categoryEntity == null){
            return null;
        }else{
            Category category = new Category();
            category.setId(categoryEntity.getId());
            category.setCode(categoryEntity.getCode());
            category.setName(categoryEntity.getName());
            return category;
        }

    }

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> categoryEntities = categoryDAO.getAll();
        List<Category> categories = new ArrayList<Category>();
        for (CategoryEntity c : categoryEntities){
            Category category = new Category();
            category.setId(c.getId());
            category.setCode(c.getCode());
            category.setName(c.getName());
            categories.add(category);
        }
        return categories;
    }
}
