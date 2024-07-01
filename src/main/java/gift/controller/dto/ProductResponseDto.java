package gift.controller.dto;

import gift.domain.Product;

public record ProductResponseDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(),
            product.getImageUrl());
    }
}
