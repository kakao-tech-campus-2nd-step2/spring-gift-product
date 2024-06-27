package gift.Model;

public class Item {
    Long id;
    String name;
    Long price;
    String imgUrl;

    public Item(Long id, String name, Long price, String imgUrl) {
        this.id = id;
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
