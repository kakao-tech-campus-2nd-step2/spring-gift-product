package gift.controller;

import static org.junit.jupiter.api.Assertions.*;

import gift.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {

    private ProductController productController;

    @BeforeEach
    public void setUp() {
        productController = new ProductController();
    }

    @Test
    @DisplayName("상품 추가 기능 확인")
    void checkAddProductMethod() {
        //given
        Product tempProduct = new Product("아이스 카페 아메리카노 T", 4500L,
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

    @Test
    @DisplayName("상품 전체 조회 기능 확인")
    void checkSelectAllProduct() {
        //given
        Product tempProduct1 = new Product("콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product("레모네이드", 2500L, "image2.jpg");
        Product tempProduct3 = new Product("밀크쉐이크", 3000L, "image3.jpg");
        List<Product> tempList;
        //when
        Product addedProduct1 = productController.addProduct(tempProduct1);
        Product addedProduct2 = productController.addProduct(tempProduct2);
        Product addedProduct3 = productController.addProduct(tempProduct3);
        tempList = productController.selectAllProduct();
        //then
        assertEquals(3, tempList.size());

        assertEquals("콜라", addedProduct1.getName());
        assertEquals(1500L, addedProduct1.getPrice());
        assertEquals("image1.jpg", addedProduct1.getImageUrl());

        assertEquals("레모네이드", addedProduct2.getName());
        assertEquals(2500L, addedProduct2.getPrice());
        assertEquals("image2.jpg", addedProduct2.getImageUrl());

        assertEquals("밀크쉐이크", addedProduct3.getName());
        assertEquals(3000L, addedProduct3.getPrice());
        assertEquals("image3.jpg", addedProduct3.getImageUrl());

    }

    @Test
    @DisplayName("단일 상품 조회 기능 확인")
    void checkSelectProductById() {
        //given
        Product tempProduct1 = new Product("콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product("레모네이드", 2500L, "image2.jpg");
        Product selectedProduct1;
        Product selectedProduct2;
        //when
        Product addedProduct1 = productController.addProduct(tempProduct1);
        Product addedProduct2 = productController.addProduct(tempProduct2);
        selectedProduct1 = productController.selectProductById(1L);
        selectedProduct2 = productController.selectProductById(2L);
        //then
        assertEquals("콜라", selectedProduct1.getName());
        assertEquals(1500L, selectedProduct1.getPrice());
        assertEquals("image1.jpg", selectedProduct1.getImageUrl());

        assertEquals("레모네이드", selectedProduct2.getName());
        assertEquals(2500L, selectedProduct2.getPrice());
        assertEquals("image2.jpg", selectedProduct2.getImageUrl());
    }

}
