package gift.product;

public class Product {

    private static Long idCounter = 1L;

    private final Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Product(String name, Integer price, String imageUrl) {
        this.id = idCounter++;
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

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
