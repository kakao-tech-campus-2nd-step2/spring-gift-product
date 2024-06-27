package gift.entity;

public class Product {
    String name, url;
    int price;

    public Product(String name, int price, String url) {
        this.name=name;
        this.price = price;
        this.url = url;
    }
}
