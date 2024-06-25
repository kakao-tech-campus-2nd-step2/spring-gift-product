package gift.domain;

public class Product {
    private int id;
    private String name;
    private int price;
    private String imageUrl;

    public Product(int id, String name, int price, String imageUrl){
        this.id=id;
        this.name=name;
        this.price=price;
        this.imageUrl=imageUrl;
    }

}
