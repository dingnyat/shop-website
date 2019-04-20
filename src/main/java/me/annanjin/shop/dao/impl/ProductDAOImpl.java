package me.annanjin.shop.dao.impl;

import me.annanjin.shop.dao.DAOAbstract;
import me.annanjin.shop.dao.ProductDAO;
import me.annanjin.shop.entity.CategoryEntity;
import me.annanjin.shop.entity.ProductEntity;
import me.annanjin.shop.model.Category;
import me.annanjin.shop.model.ProductFilterRequest;
import me.annanjin.shop.util.search.SearchCriteriaConsumer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Repository
@Transactional
public class ProductDAOImpl extends DAOAbstract<Integer, ProductEntity> implements ProductDAO {
    @Override
    public List<ProductEntity> getProductsByCategoryWithFilter(ProductFilterRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> productEntityRoot = criteriaQuery.from(ProductEntity.class);
        Join<ProductEntity, CategoryEntity> categoryEntityListJoin = productEntityRoot.join("categories", JoinType.INNER);

        List<Predicate> categoryPredicates = new ArrayList<>();
        Stack<Category> stack = new Stack<>();
        stack.push(request.getCategory());
        while (!stack.isEmpty()) {
            Category category = stack.pop();
            category.getChildCategories().forEach(stack::push);
            categoryPredicates.add(criteriaBuilder.equal(categoryEntityListJoin.get("code"), category.getCode()));
        }

        List<Predicate> searchPredicates = new ArrayList<>();
        if (request.getSearchCriteriaList() != null) {
            SearchCriteriaConsumer searchCriteriaConsumer = new SearchCriteriaConsumer(criteriaBuilder, productEntityRoot);
            request.getSearchCriteriaList().forEach(searchCriteria -> searchPredicates.add(searchCriteriaConsumer.createPredicate(searchCriteria)));
        }

        criteriaQuery
                .select(productEntityRoot)
                .where(criteriaBuilder.and(criteriaBuilder.and(searchPredicates.toArray(new Predicate[]{})),
                        criteriaBuilder.or(categoryPredicates.toArray(new Predicate[]{}))));

        if (request.getOrder().getDirection().equalsIgnoreCase("asc")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productEntityRoot.get(request.getOrder().getProperty())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productEntityRoot.get(request.getOrder().getProperty())));
        }

        TypedQuery<ProductEntity> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(request.getStart());
        query.setMaxResults(request.getLength());
        return query.getResultList();
    }

    @Override
    public Long getFilteredRecordsTotal(ProductFilterRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<ProductEntity> productEntityRoot = criteriaQuery.from(ProductEntity.class);
        Join<ProductEntity, CategoryEntity> categoryEntityListJoin = productEntityRoot.join("categories", JoinType.INNER);

        List<Predicate> categoryPredicates = new ArrayList<>();
        Stack<Category> stack = new Stack<>();
        stack.push(request.getCategory());
        while (!stack.isEmpty()) {
            Category category = stack.pop();
            category.getChildCategories().forEach(stack::push);
            categoryPredicates.add(criteriaBuilder.equal(categoryEntityListJoin.get("code"), category.getCode()));
        }

        List<Predicate> searchPredicates = new ArrayList<>();
        if (request.getSearchCriteriaList() != null) {
            SearchCriteriaConsumer searchCriteriaConsumer = new SearchCriteriaConsumer(criteriaBuilder, productEntityRoot);
            request.getSearchCriteriaList().forEach(searchCriteria -> searchPredicates.add(searchCriteriaConsumer.createPredicate(searchCriteria)));
        }

        criteriaQuery
                .select(criteriaBuilder.count(productEntityRoot))
                .where(criteriaBuilder.and(criteriaBuilder.and(searchPredicates.toArray(new Predicate[]{})),
                        criteriaBuilder.or(categoryPredicates.toArray(new Predicate[]{}))));

        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public ProductEntity getByCode(String code) {
        String hql = "SELECT p FROM ProductEntity p WHERE p.code = '" + code + "'";
        Query query = entityManager.createQuery(hql, ProductEntity.class);
        return (ProductEntity) query.getSingleResult();
    }
}
