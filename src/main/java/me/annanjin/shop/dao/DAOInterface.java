package me.annanjin.shop.dao;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<PrimaryKeyType extends Serializable, T> {
    PrimaryKeyType add(T entity);

    void update(T entity);

    void remove(T entity);

    T getById(PrimaryKeyType id);

    List<T> getAll();
}