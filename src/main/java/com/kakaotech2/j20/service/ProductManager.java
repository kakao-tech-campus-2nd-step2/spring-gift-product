package com.kakaotech2.j20.service;

import com.kakaotech2.j20.Product;
import java.util.List;

public interface ProductManager {
    //상품 리스트 전체 조회
    List<Product> readAll();
    //새 상품 생성
    void create(Product prod);
    //상품 이름 변경
    void updateName(long id,String name);
    //상품 가격 변경
    void updatePrice(long id,int price);
    //상품 이미지 변경
    void updateImageUrl(long id,String url);
    //상품 삭제
    void delete(long id);

}
