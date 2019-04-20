package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity buyer;

    @Column(name = "date_buy", nullable = false)
    private Date dateBuy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", nullable = false)
    private Set<CartItemEntity> cartItems;

    public CartEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(AccountEntity buyer) {
        this.buyer = buyer;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Set<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }
}
