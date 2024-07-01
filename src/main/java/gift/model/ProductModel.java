package gift.model;

public class ProductModel {
    public long id; // 상품의 고유 식별자
    public String category; // 상품 카테고리
    public String name; // 상품 이름
    public int price; // 상품 가격

    // 생성자
    public ProductModel(long id, String category, String name, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    // 기본 생성자 : 매개변수 없이 호출될 수 있게 한다.
    public ProductModel() {
    }

    // Getter와 Setter 메서드
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}