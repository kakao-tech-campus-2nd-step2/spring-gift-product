package gift;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty
    Long id;
    @JsonProperty
    String name;
    @JsonProperty
    int price;
    @JsonProperty
    String imageUrl;

    public Product(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
