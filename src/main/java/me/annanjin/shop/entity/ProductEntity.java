package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 265)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false)},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "category_id"})}
    )
    private Set<CategoryEntity> categories;

    @Column(name = "description", nullable = false, length = 2048)
    private String description;

    @Column(name = "thumbnail_url", nullable = false, length = 1024)
    private String thumbnailUrl;

    public ProductEntity() {
    }

    public ProductEntity(String name, int quantity, double price, Set<CategoryEntity> categories, String description, String thumbnailUrl) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
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

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
