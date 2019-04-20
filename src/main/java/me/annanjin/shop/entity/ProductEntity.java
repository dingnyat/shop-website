package me.annanjin.shop.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", nullable = false, length = 32)
    private String code;

    @Column(name = "name", nullable = false, length = 265)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "status", nullable = false, length = 64)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_product_product_category"))},
            inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_product_category_category"))},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "category_id"})})
    private Set<CategoryEntity> categories;

    @Column(name = "summary_description")
    private String summaryDesc;

    @Column(name = "description", nullable = false, length = 65536, columnDefinition = "TEXT")
    @Type(type = "text")
    private String description;

    @Column(name = "thumbnail_url", nullable = false, length = 1024)
    private String thumbnailUrl;

    @Column(name = "json_of_images", nullable = false, length = 65536, columnDefinition = "TEXT")
    @Type(type = "text")
    private String JSONOfImages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<ReviewEntity> reviews;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<ProductSpecEntity> productSpecs;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id", nullable = false, insertable = false, updatable = false)
    private BrandEntity brand;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<DiscountEntity> discounts;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "publishing_time", nullable = false)
    private Date publishingTime;

    public ProductEntity() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getSummaryDesc() {
        return summaryDesc;
    }

    public void setSummaryDesc(String summaryDesc) {
        this.summaryDesc = summaryDesc;
    }

    public String getJSONOfImages() {
        return JSONOfImages;
    }

    public void setJSONOfImages(String JSONOfImages) {
        this.JSONOfImages = JSONOfImages;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public List<ProductSpecEntity> getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(List<ProductSpecEntity> productSpecs) {
        this.productSpecs = productSpecs;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public List<DiscountEntity> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountEntity> discounts) {
        this.discounts = discounts;
    }

    public Date getPublishingTime() {
        return publishingTime;
    }

    public void setPublishingTime(Date publishingTime) {
        this.publishingTime = publishingTime;
    }
}
