package gift.dto;

import gift.domain.Product;

public class ProductRequestDto {
    private String name;
    private int price;
    private String imageUrl;

    public Product toEntity() {
        return new Product(this.name,this.price,this.imageUrl);
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
