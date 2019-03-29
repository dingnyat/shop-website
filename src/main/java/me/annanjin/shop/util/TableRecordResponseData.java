package me.annanjin.shop.util;

import java.util.List;

public class TableRecordResponseData<T> {
    private List<T> data;
    private long recordsTotal;
    private long recordsFilterd;

    public TableRecordResponseData(List<T> data) {
        this.data = data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFilterd() {
        return recordsFilterd;
    }

    public void setRecordsFilterd(long recordsFilterd) {
        this.recordsFilterd = recordsFilterd;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
