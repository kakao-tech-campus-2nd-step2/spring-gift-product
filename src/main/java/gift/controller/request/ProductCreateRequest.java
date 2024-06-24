package gift.controller.request;

import gift.domain.Product;

public class ProductCreateRequest {

    private String name;
    private int price;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product toEntity() {
        return new Product(this.name, this.price, this.imageUrl);
    }

}
