package gift.Model;

public class ItemDTO {

    private String name;
    private Long price;
    private String imgUrl;

    public  ItemDTO(){}

    public ItemDTO(String name, Long price, String imgUrl) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", imgUrl='" + imgUrl + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
