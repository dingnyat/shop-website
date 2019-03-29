package me.annanjin.shop.service;

import me.annanjin.shop.util.search.SearchCriteria;

import java.io.Serializable;
import java.util.List;

public interface ServiceInterface<PrimaryKeyType extends Serializable, M> {
    PrimaryKeyType add(M model);

    void update(M model);

    void remove(PrimaryKeyType id);

    M getById(PrimaryKeyType id);

    List<M> getAll();

    List<M> search(List<SearchCriteria> searchCriteria);

    Long count(List<SearchCriteria> searchCriteria);

    Long countTotal();
}
