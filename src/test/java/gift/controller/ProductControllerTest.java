package gift.controller;

import static org.junit.jupiter.api.Assertions.*;

import gift.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {

    private ProductController productController;
    private Product tempProduct;

    @BeforeEach
    public void setUp() {
        productController = new ProductController();
    }

    @Test
    @DisplayName("상품 추가 기능 확인")
    void checkAddProductMethod() {
        //given
        tempProduct = new Product("아이스 카페 아메리카노 T", 4500L,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        //when
        Product addedProduct = productController.addProduct(tempProduct);
        //then
        assertEquals(1L, addedProduct.getId());
        assertEquals("아이스 카페 아메리카노 T", addedProduct.getName());
        assertEquals(4500, addedProduct.getPrice());
        assertEquals(
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg",
            addedProduct.getImageUrl());
    }

}
