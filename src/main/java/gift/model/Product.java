package gift.model;

import gift.dto.ProductResponseDto;

public class Product {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(ProductResponseDto productResponseDto) {
        this.id = productResponseDto.id();
        this.name = productResponseDto.name();
        this.price = productResponseDto.price();
        this.imageUrl = productResponseDto.imageUrl();
    }


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

}
