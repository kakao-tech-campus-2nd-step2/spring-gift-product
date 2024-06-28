package gift.web.dto.response;

import gift.domain.Product;
import java.net.URL;

public class UpdateProductResponse {

    private Long id;
    private String name;
    private Integer price;
    private URL imageUrl;

    private UpdateProductResponse(Long id, String name, Integer price, URL imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static UpdateProductResponse from(Product product) {
        return new UpdateProductResponse(product.getId(), product.getName(), product.getPrice(),
            product.getImageUrl());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public URL getImageUrl() {
        return imageUrl;
    }
}