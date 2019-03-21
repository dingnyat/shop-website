package me.annanjin.shop.model;

import java.io.Serializable;

public abstract class CommonModel<PrimaryKeyType extends Serializable> {

    protected PrimaryKeyType id;

    public PrimaryKeyType getId() {
        return id;
    }

    public void setId(PrimaryKeyType id) {
        this.id = id;
    }
}
