package gift.restapi.dto.response;

import gift.core.Product;

public record ProductResponse(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.id(), product.name(), product.price(), product.imageUrl());
    }
}
