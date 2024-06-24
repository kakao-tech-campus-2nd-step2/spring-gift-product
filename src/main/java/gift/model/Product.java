package gift.model;

public class Product {
    private Long Id;
    private String Name;
    private int price;
    private String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        Id = id;
        Name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
