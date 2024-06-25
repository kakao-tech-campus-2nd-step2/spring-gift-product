package gift;

public record Product(long id,String name,int price,String imageUrl) {
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}
