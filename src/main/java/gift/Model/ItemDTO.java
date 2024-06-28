package gift.Model;

import java.util.Objects;

public class ItemDTO {
    private String name;
    private Long price;
    private String imgUrl;
    public ItemDTO(String name, Long price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public String getName() {
        return name;
    }
    public Long getPrice() {
        return price;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}
