package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "star", nullable = false)
    private int star;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "content", nullable = false, length = 1024)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private ProductEntity product;

    public ReviewEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
