package gift.entity;


//import jakarta.persistence.Entity;

//@Entity
public class Product {
    private String name, url;
    private int price;

    public Product(String name, int price, String url) {
        this.name=name;
        this.price = price;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
