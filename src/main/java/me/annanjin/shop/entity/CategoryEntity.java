package me.annanjin.shop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parent_child_category",
            joinColumns = @JoinColumn(name = "parent_category_id"),
            inverseJoinColumns = @JoinColumn(name = "child_category_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"child_category_id", "parent_category_id"}))
    private Set<CategoryEntity> childCategories;

    @ManyToMany(mappedBy = "childCategories", fetch = FetchType.EAGER)
    private Set<CategoryEntity> parentCategories;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    public Set<CategoryEntity> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<CategoryEntity> childCategories) {
        this.childCategories = childCategories;
    }

    public Set<CategoryEntity> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(Set<CategoryEntity> parentCategories) {
        this.parentCategories = parentCategories;
    }
}
