package gift.vo;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product() { }

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    // getter
    public Long getId() {
        return id;
    }

}
