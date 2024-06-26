package gift;

import java.util.concurrent.atomic.AtomicLong;

public class Product {

    private static AtomicLong cnt = new AtomicLong(0);
    private Long id;
    private String name;
    private Integer price;
    private String imageUrl;

    public Product(){
    }
    public Product(String name, Integer price, String imageUrl){
        this.id = autoIncrement();
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private Long autoIncrement(){
        return cnt.incrementAndGet();
    }
}
