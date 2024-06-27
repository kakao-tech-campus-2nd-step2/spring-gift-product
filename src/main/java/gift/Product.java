package gift;

public record Product(Long id, String name, Integer price, String imageUrl) {
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Integer getPrice(){
        return price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}
