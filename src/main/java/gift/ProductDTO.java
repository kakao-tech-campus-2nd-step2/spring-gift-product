package gift;

public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String imageUrl;
    public ProductDTO(Long id, String name, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }
}
