package me.annanjin.shop.dao;

import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.search.SearchCriteria;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<PrimaryKeyType extends Serializable, E> {
    PrimaryKeyType create(E entity);

    void update(E entity);

    void delete(E entity);

    E getById(PrimaryKeyType id);

    List<E> getAllRecords();

    List<E> search(List<SearchCriteria> searchCriteria);

    Long getTheNumberOfSearchedRecords(List<SearchCriteria> searchCriteria);

    List<E> getTableData(DataTableRequest dataTableRequest, String... fieldNames);

    Long getTheNumberOfFilteredRecords(DataTableRequest dataTableRequest, String... fieldNames);

    Long getTheNumberOfAllRecords();
}