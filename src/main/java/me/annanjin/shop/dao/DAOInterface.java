package me.annanjin.shop.dao;

import me.annanjin.shop.util.search.SearchCriteria;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<PrimaryKeyType extends Serializable, E> {
    PrimaryKeyType add(E entity);

    void update(E entity);

    void remove(E entity);

    E getById(PrimaryKeyType id);

    List<E> getAll();

    List<E> search(List<SearchCriteria> searchCriteria);

    Long count(List<SearchCriteria> searchCriteria);

    Long countTotal();
}