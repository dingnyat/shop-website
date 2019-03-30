package me.annanjin.shop.service;

import me.annanjin.shop.util.datatable.DataTableRequest;
import me.annanjin.shop.util.search.SearchCriteria;

import java.io.Serializable;
import java.util.List;

public interface ServiceInterface<PrimaryKeyType extends Serializable, M> {
    PrimaryKeyType create(M model);

    void update(M model);

    void delete(PrimaryKeyType id);

    M getById(PrimaryKeyType id);

    List<M> getAllRecords();

    List<M> search(List<SearchCriteria> searchCriteria);

    Long getTheNumberOfSearchedRecords(List<SearchCriteria> searchCriteria);

    List<M> getTableData(DataTableRequest dataTableRequest);

    Long getTheNumberOfFilteredRecords(DataTableRequest dataTableRequest);

    Long getTheNumberOfAllRecords();
}
