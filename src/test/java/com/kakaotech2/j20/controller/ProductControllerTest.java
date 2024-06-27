package com.kakaotech2.j20.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {


    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("전체 목록 조회")
    void getList() {
        //given
        ProductDTO productDTO = getProductDTO();

        productService.create(productDTO);

        //when

        var request = productController.getList();

        //then
        assertNotNull(request);
        assertEquals(productDTO.getName(),request.get(0).getName());
        assertEquals(productDTO.getPrice(),request.get(0).getPrice());
        assertEquals(productDTO.getImageUrl(),request.get(0).getImageUrl());

    }

    private static ProductDTO getProductDTO() {
        var name = "coffee";
        var price = 123123;
        var imgUrl = "imgimg";
        return new ProductDTO(null, name, price, imgUrl);
    }
}