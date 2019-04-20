package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "discount")
public class DiscountEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discount_price", nullable = false)
    private float discountPrice;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "starting_time", nullable = false)
    private Date startingTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "finishing_time", nullable = false)
    private Date finishingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductEntity product;

    public DiscountEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
