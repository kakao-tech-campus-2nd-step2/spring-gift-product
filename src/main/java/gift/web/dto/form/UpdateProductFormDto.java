package gift.web.dto.form;

import gift.domain.Product;
import java.net.URL;

public class UpdateProductFormDto {

    private Long id;
    private String name;
    private Integer price;
    private URL imageUrl;

    private UpdateProductFormDto(Long id, String name, Integer price, URL imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static UpdateProductFormDto from(Product product) {
        return new UpdateProductFormDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
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