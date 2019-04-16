package me.annanjin.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItem extends CommonModel<Integer> {
    private Product product;
    private int buyQuantity;
    private double sellPrice;
    @JsonIgnore // ngat vong lap
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Product product, int buyQuantity, double sellPrice, Cart cart) {
        this.product = product;
        this.buyQuantity = buyQuantity;
        this.sellPrice = sellPrice;
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
