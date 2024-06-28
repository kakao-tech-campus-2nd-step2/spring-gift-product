package gift.domain.product.dto;

import gift.domain.product.entity.Product;

public record ProductDto(Long id, String name, String description, int price, String imageUrl) {

    public Product toProduct() {
        return new Product(null, name, price, imageUrl);
    }
}
