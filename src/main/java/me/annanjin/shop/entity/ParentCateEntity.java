package me.annanjin.shop.entity;

import javax.persistence.*;

@Entity
@Table(name = "parent_category")
public class ParentCateEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_id", nullable = false)
    private int parentId;

    @Column(name = "child_id", nullable = false)
    private int childId;

    public ParentCateEntity() {
    }

    public ParentCateEntity(int parentId, int childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}
