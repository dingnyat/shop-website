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

}
