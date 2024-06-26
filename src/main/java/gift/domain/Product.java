package gift.domain;

import gift.dto.ProductRequest;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public void update(ProductRequest request) {
        this.name = request.name();
        this.price = request.price();
        this.imageUrl = request.imageUrl();
    }
}
