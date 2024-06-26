package gift.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gift.model.Product;
import gift.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    @DisplayName("상품 추가 기능 확인")
    void checkAddProductMethod() {
        //given
        Product tempProduct = new Product("아이스 카페 아메리카노 T", 4500L,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        //when
        Product addedProduct = productService.addProduct(tempProduct);
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
        Product addedProduct1 = productService.addProduct(tempProduct1);
        Product addedProduct2 = productService.addProduct(tempProduct2);
        Product addedProduct3 = productService.addProduct(tempProduct3);
        tempList = productService.selectAllProduct();
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
        Product addedProduct1 = productService.addProduct(tempProduct1);
        Product addedProduct2 = productService.addProduct(tempProduct2);
        selectedProduct1 = productService.selectProductById(1L);
        selectedProduct2 = productService.selectProductById(2L);
        //then
        assertEquals("콜라", selectedProduct1.getName());
        assertEquals(1500L, selectedProduct1.getPrice());
        assertEquals("image1.jpg", selectedProduct1.getImageUrl());

        assertEquals("레모네이드", selectedProduct2.getName());
        assertEquals(2500L, selectedProduct2.getPrice());
        assertEquals("image2.jpg", selectedProduct2.getImageUrl());
    }

    @Test
    @DisplayName("상품 삭제 기능 확인")
    void checkDeleteProduct() {
        //given
        Product tempProduct1 = new Product("콜라", 1500L, "image1.jpg");
        Long selecteLong;
        //when
        Product addedProduct1 = productService.addProduct(tempProduct1);
        productService.deleteProduct(1L);

        //then
        assertThrows(IllegalArgumentException.class,
            () -> productService.deleteProduct(2L));
        assertEquals(0, productService.selectAllProduct().size());
    }

    @Test
    @DisplayName("상품 수정 기능 확인")
    void checkUpdateProduct() {
        //given
        Product tempProduct1 = new Product("콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product("아이스크림", 1000L, "image1.jpg");
        //when
        productService.addProduct(tempProduct1);
        Product updatedProduct1 = productService.updateProduct(1L, tempProduct2);
        //then
        assertEquals("아이스크림", updatedProduct1.getName());
        assertEquals(1000L, updatedProduct1.getPrice());
        assertThrows(IllegalArgumentException.class,
            () -> productService.updateProduct(2L, tempProduct2));
        assertEquals(1, productService.selectAllProduct().size());
    }
}
