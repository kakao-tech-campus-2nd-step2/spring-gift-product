package gift.domain;

import gift.dto.ProductRequest;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
    }

    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    public Product(ProductRequest request) {
        this.id = request.id();
        this.name = request.name();
        this.price = request.price();
        this.imageUrl = request.imageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void update(ProductRequest request) {
        this.name = request.name();
        this.price = request.price();
        this.imageUrl = request.imageUrl();
    }
}
