package gift;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private double price;
    private String imageUrl;

    public Product(UUID id, String name, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
