package gift;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(String name, int price, String imageUrl) {
        this.id = 0L;
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

    public void setId(Long id) {
        this.id = id;
    }

     public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProduct(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
    }
}