package me.annanjin.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Brand extends CommonModel<Integer> {
    private String name;
    private String code;
    @JsonIgnore
    private List<Product> products;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
