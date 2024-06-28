package gift.model;

public class ProductForm {

    private String name;
    private Integer price;
    private String imageUrl;

    // 생성자
    public ProductForm(String name, Integer price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // getter 메서드들
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // setter 메서드들
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 객체를 문자열로 표현하는 메서드
    @Override
    public String toString() {
        return "ProductForm{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
    }

}
