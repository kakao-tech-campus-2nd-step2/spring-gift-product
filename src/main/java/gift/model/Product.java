package gift.model;
/*
- 데이터 구조를 정의, 비즈니스 로직을 포함하지 않음
- DTO 클래스 포함
- 주로 JSON 형태의 데이터를 표현하는데 사용
 */

public class Product {
    // 필드 생성
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    // 생성자
    public Product(Long id, String name, int price, String imageUrl){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getter 메서드들
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

    // Setter 메서드들
    public void setId(Long id) {
        this.id = id;
    }

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
