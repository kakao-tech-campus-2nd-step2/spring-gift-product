package com.kakaotech2.j20.DTO;

public class ProductDTO {
    //DTO는 생성 후 변화하지 않으므로 final 지정

    private final long id;
    private final String name;

    private final int price;

    private final String imageUrl;

    public ProductDTO(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
