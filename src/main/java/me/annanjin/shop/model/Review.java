package me.annanjin.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Review extends CommonModel<Integer> {
    private int star;
    private String name;
    private String email;
    private String content;
    private Date time;
    @JsonIgnore
    private Product product;

    public Review() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
