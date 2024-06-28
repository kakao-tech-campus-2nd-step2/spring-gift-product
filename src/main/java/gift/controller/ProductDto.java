package gift.controller;

import gift.domain.Product;

public class ProductDto {
    private String name;
    private long price;
    private String imageUrl;

    public ProductDto(String name, long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDto() {
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getPrice(){
        return price;
    }
    public void setPrice(long price){
        this.price = price;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public static ProductDto entityToDto(Product product){
        return new ProductDto(product.getName(), product.getPrice(), product.getImageUrl());
    }
}
