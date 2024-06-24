package gift;

public class Product {
    Long id;
    String name;
    int price;
    String imageUrl;

    public Product(Long id, String name, int price, String imageUrl){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    public void setProduct(Product product){
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
        this.imageUrl = product.imageUrl;
    }
}
