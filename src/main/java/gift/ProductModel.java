package gift;

public class ProductModel {
    public long id; // 상품의 고유 식별자
    public String name; // 상품 이름
    public int price; // 상품 가격
    public String imageUrl; // 상품 이미지 URL

    // 생성자
    public ProductModel(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // 기본 생성자 : 매개변수 없이 호출될 수 있게 한다.
    public ProductModel() {
    }
}