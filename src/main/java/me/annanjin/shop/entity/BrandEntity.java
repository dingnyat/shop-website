package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class BrandEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "code", nullable = false, length = 32, unique = true)
    private String code;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private List<ProductEntity> products;

    public BrandEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
