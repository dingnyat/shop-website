package me.annanjin.shop.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_category",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "category_id"})})
public class ProductCategoryEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    public ProductCategoryEntity() {
    }

    public ProductCategoryEntity(int id, ProductEntity productEntity, CategoryEntity categoryEntity) {
        this.id = id;
        this.productEntity = productEntity;
        this.categoryEntity = categoryEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
