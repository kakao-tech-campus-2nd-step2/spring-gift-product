package com.kakaotech2.j20;

public class Product {

    private final long id;

    private String name;

    private int price;

    private String imageUrl;

    public Product(long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
