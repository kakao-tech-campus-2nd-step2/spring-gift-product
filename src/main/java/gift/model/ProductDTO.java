package gift.model;

public record ProductDTO(String name, int price, String imageUrl) {

    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}
