package gift;

import java.util.UUID;

public class Product {
    private long id;
    private String name;
    private double price;
    private String imageUrl;

    public Product(long id, String name, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
