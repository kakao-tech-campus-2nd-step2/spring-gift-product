package gift.model;

public class Product {

    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    // 기본 생성자 추가 (JPA나 다른 프레임워크에서 필요할 수 있음)
    public Product() {
    }

    // 생성자
    public Product(String name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // ProductForm을 Product로 변환하는 생성자
    public Product(ProductForm form) {
        this.name = form.getName();
        this.price = form.getPrice();
        this.imageUrl = form.getImageUrl();
    }

    // getter 메서드들
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

    // setter 메서드들
    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 주어진 Product 객체의 정보를 현재 객체에 업데이트하는 메서드
    public void updateFrom(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    // 객체를 문자열로 표현하는 메서드
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
