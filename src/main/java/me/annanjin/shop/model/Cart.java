package me.annanjin.shop.model;

import java.util.Date;
import java.util.Set;

public class Cart extends CommonModel<Integer> {
    private Account buyer;
    private Date dateBuy;
    private Set<CartItem> cartItems;

    public Cart() {
    }

    public Account getBuyer() {
        return buyer;
    }

    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
