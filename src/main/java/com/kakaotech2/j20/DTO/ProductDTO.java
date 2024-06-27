package com.kakaotech2.j20.DTO;

import com.kakaotech2.j20.Product;

public class ProductDTO {
    //DTO는 생성 후 변화하지 않으므로 final 지정

    private final Long id;

    private final String name;

    private final Integer price;

    private final String imageUrl;

    public ProductDTO(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    //상품 모델을 DTO로 빠르게 전환
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }
}
