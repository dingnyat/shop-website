package me.annanjin.shop.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DAOAbstract<PrimaryKeyType extends Serializable, T> {

    private final Class<T> persistentClass;

    private final String entityName;

    @PersistenceContext
    protected EntityManager entityManager;

    protected DAOAbstract() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        final int index = this.persistentClass.getName().lastIndexOf('.');
        entityName = this.persistentClass.getName().substring(index + 1);
    }

    public PrimaryKeyType add(T entity) {
        entityManager.persist(entity);
        return (PrimaryKeyType) ((Session) entityManager.getDelegate()).getIdentifier(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public T getById(PrimaryKeyType id) {
        return entityManager.find(persistentClass, id);
    }

    public List<T> getAll() {
        persistentClass.getName();
        return entityManager.createQuery("SELECT a FROM " + entityName + " a", persistentClass).getResultList();
    }
}
