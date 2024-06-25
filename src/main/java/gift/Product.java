package gift;

public class Product {
    Long id;
    String name, imageUrl;
    int price;

    public Product(Long id, String name, int price, String imageUrl) {
        if(id == null) {
            throw new IllegalArgumentException("[ERROR] ID는 비워둘 수 없습니다.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getter
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

    // Setter
    // ID는 바뀔 수 없음
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
