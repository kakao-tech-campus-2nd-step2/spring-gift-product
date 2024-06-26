package gift.model;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private String imgUrl;

    public Product(Long id, String name, Integer price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(Long id) {
        if(this.id == null){
            this.id = id;
        }
    }
}
