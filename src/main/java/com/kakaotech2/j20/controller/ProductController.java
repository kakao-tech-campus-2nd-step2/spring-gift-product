package com.kakaotech2.j20.controller;

import com.kakaotech2.j20.ProductManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품 추가,수정,삭제,조회를 위한 api end-point
 * <p>
 * $/api/products
 */
@RestController
public class ProductController {

    private final ProductManager pm;

    public ProductController(ProductManager pm) {
        this.pm = pm;
    }



}
