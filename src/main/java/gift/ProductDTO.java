package gift;

public record ProductDTO(Long id, String name, int price, String imageUrl) {

    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
