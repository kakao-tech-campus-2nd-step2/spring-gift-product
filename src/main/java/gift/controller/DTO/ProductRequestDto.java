package gift.controller.DTO;

import gift.model.Product;

public class ProductRequestDto {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product toEntity(){
        return new Product(
            id,
            name,
            price,
            imageUrl
        );
    }
}
