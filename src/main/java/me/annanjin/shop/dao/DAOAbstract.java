package me.annanjin.shop.dao;

import me.annanjin.shop.util.search.SearchCriteria;
import me.annanjin.shop.util.search.SearchCriteriaConsumer;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOAbstract<PrimaryKeyType extends Serializable, E> {

    private final Class<E> entityClazz;

    private final String entityName;

    @PersistenceContext
    protected EntityManager entityManager;

    protected DAOAbstract() {
        this.entityClazz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        final int index = this.entityClazz.getName().lastIndexOf('.');
        entityName = this.entityClazz.getName().substring(index + 1);
    }

    public PrimaryKeyType add(E entity) {
        entityManager.persist(entity);
        return (PrimaryKeyType) ((Session) entityManager.getDelegate()).getIdentifier(entity);
    }

    public void update(E entity) {
        entityManager.merge(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

    public E getById(PrimaryKeyType id) {
        return entityManager.find(entityClazz, id);
    }

    public List<E> getAll() {
        entityClazz.getName();
        return entityManager.createQuery("SELECT a FROM " + entityName + " a", entityClazz).getResultList();
    }

    public List<E> search(List<SearchCriteria> searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
        Root<E> root = criteriaQuery.from(entityClazz);
        Predicate predicate = criteriaBuilder.conjunction();
        SearchCriteriaConsumer searchCriteriaConsumer = new SearchCriteriaConsumer(predicate, criteriaBuilder, root);
        searchCriteria.forEach(searchCriteriaConsumer);
        predicate = searchCriteriaConsumer.getPredicate();
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Long count(List<SearchCriteria> searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = criteriaQuery.from(entityClazz);
        Predicate predicate = criteriaBuilder.conjunction();
        SearchCriteriaConsumer searchCriteriaConsumer = new SearchCriteriaConsumer(predicate, criteriaBuilder, root);
        searchCriteria.forEach(searchCriteriaConsumer);
        predicate = searchCriteriaConsumer.getPredicate();
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root))).getSingleResult();
    }

    public Long countTotal() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = criteriaQuery.from(entityClazz);
        criteriaQuery.where((new ArrayList<Predicate>()).toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root))).getSingleResult();
    }
}
