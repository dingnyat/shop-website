package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.model.ProductFilterRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Repository
@Transactional
public class ProductDAOImpl extends DAOAbstract<Integer, ProductEntity> implements ProductDAO {
    @Override
    public List<ProductEntity> getProductsByCategoryWithFilter(ProductFilterRequest request) {
        List<String> conditions = new ArrayList<>();
        Stack<Category> stack = new Stack<>();
        stack.push(request.getCategory());
        while (!stack.isEmpty()) {
            Category category = stack.pop();
            category.getChildCategories().forEach(stack::push);
            conditions.add("c.code = \'" + category.getCode() + "\'");
        }

        StringBuilder conditionExpression = new StringBuilder();

        for (int i = 0; i < conditions.size() - 1; i++) {
            conditionExpression.append(conditions.get(i)).append(" OR ");
        }

        conditionExpression.append(conditions.get(conditions.size() - 1));

        String hql = "SELECT p FROM CategoryEntity c JOIN c.products p WHERE " + conditionExpression.toString() + " " +
                "ORDER BY p." + request.getOrder().getProperty() + " " + request.getOrder().getDirection().toUpperCase();

        Query query = entityManager.createQuery(hql, ProductEntity.class);
        query.setFirstResult(request.getStart());
        query.setMaxResults(request.getLength());
        return query.getResultList();
    }

    @Override
    public Long getRecordsTotal(ProductFilterRequest request) {
        List<String> conditions = new ArrayList<>();
        Stack<Category> stack = new Stack<>();
        stack.push(request.getCategory());
        while (!stack.isEmpty()) {
            Category category = stack.pop();
            category.getChildCategories().forEach(stack::push);
            conditions.add("c.code = \'" + category.getCode() + "\'");
        }

        StringBuilder conditionExpression = new StringBuilder();

        for (int i = 0; i < conditions.size() - 1; i++) {
            conditionExpression.append(conditions.get(i)).append(" OR ");
        }

        conditionExpression.append(conditions.get(conditions.size() - 1));

        String hql = "SELECT COUNT(p) FROM CategoryEntity c JOIN c.products p WHERE " + conditionExpression.toString() + " " +
                "ORDER BY p." + request.getOrder().getProperty() + " " + request.getOrder().getDirection().toUpperCase();
        Query query = entityManager.createQuery(hql, Long.class);
        return (Long) query.getSingleResult();
    }
}
