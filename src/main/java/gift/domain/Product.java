package gift.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() {
        this.id = null;
        this.name = null;
        this.price = 0;
        this.imageUrl = null;
    }

    // constructor (use id)
    public Product(Long id, String name, int price, String imageUrl) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.price = price;
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl must not be null");
    }

    // constructor (don't use id)
    public Product(String name, int price, String imageUrl) {
        this.id = null;
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.price = price;
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl must not be null");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setPrice(int price) {
        this.price = price;
    }
}
