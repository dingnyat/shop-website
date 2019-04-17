package me.annanjin.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class Category extends CommonModel<Integer> {
    private String code;
    private String name;
    private Set<Category> childCategories;
    @JsonIgnore
    private Set<Category> parentCategories;
    @JsonIgnore
    private Set<Product> products;

    public Category() {
    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

    public Set<Category> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(Set<Category> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
