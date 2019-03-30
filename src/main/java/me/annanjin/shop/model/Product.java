package me.annanjin.shop.model;

import java.util.Set;

public class Product extends CommonModel<Integer> {

    private String name;
    private int quantity;
    private double price;
    private Set<Category> categories;
    private String description;
    private String thumbnailUrl;

    public Product() {
    }

    public Product(String name, int quantity, double price, Set<Category> categories, String description, String thumbnailUrl) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
