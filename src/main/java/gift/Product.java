package gift;

public record Product(Long id, String name, Integer price, String imageUrl) {
    public Product {
        if(id == null) {
            throw new IllegalArgumentException("[ERROR] ID는 비워둘 수 없습니다.");
        }
    }
}
