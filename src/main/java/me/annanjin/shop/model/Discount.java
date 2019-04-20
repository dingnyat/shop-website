package me.annanjin.shop.model;

import java.util.Date;
import java.util.List;

public class Discount extends CommonModel<Integer> {
    private float discountPrice;
    private String description;
    private Date startingTime;
    private Date finishingTime;
    private List<Product> products;

    public Discount() {
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(Date finishingTime) {
        this.finishingTime = finishingTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
