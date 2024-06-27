package gift.domain.model;

import gift.domain.model.Product;

public class ProductRequestDto {

    Long id;
    String name;
    Long price;
    String imageUrl;

    public ProductRequestDto(Long id, String name, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public Product toProduct() {
        return new Product(name, price, imageUrl);
    }

}
