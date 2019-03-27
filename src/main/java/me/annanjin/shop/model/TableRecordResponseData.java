package me.annanjin.shop.model;

import java.util.List;

public class TableRecordResponseData<T> {
    private List<T> data;

    public TableRecordResponseData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
