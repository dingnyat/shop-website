package me.annanjin.shop.entity;


import javax.persistence.*;

@Entity
@Table(name = "parent_category", uniqueConstraints = {@UniqueConstraint(columnNames = {"parent_id", "child_id"})})
public class ParentCateEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private CategoryEntity parentCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private CategoryEntity childCategory;

    public ParentCateEntity() {
    }

    public ParentCateEntity(CategoryEntity parentCategory, CategoryEntity childCategory) {
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }

    public CategoryEntity getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(CategoryEntity childCategory) {
        this.childCategory = childCategory;
    }
}
