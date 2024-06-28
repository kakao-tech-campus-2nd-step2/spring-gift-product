package gift.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gift.model.Product;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("상품 추가 기능 확인")
    void checkAddProductMethod() {
        //given
        Product tempProduct = new Product(1L,"아이스 카페 아메리카노 T", 4500L, "image1.jpg");
        //when
        productService.addProduct(tempProduct);
        //then
        assertEquals(1L, productService.getProductById(1L).getId());
        assertEquals("아이스 카페 아메리카노 T", productService.getProductById(1L).getName());
        assertEquals(4500, productService.getProductById(1L).getPrice());
        assertEquals("image1.jpg", productService.getProductById(1L).getImageUrl());
    }

    @Test
    @DisplayName("상품 전체 조회 기능 확인")
    void checkSelectAllProduct() {
        //given
        Product tempProduct1 = new Product(1L,"콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product(2L,"레모네이드", 2500L, "image2.jpg");
        Product tempProduct3 = new Product(3L,"밀크쉐이크", 3000L, "image3.jpg");
        List<Product> tempList;
        //when
        productService.addProduct(tempProduct1);
        productService.addProduct(tempProduct2);
        productService.addProduct(tempProduct3);
        tempList = productService.getAllProducts();
        //then
        assertEquals(3, tempList.size());

        assertEquals("콜라", tempList.get(1).getName());
        assertEquals(1500L, tempList.get(1).getPrice());
        assertEquals("image1.jpg", tempList.get(1).getImageUrl());

        assertEquals("레모네이드", tempList.get(2).getName());
        assertEquals(2500L, tempList.get(2).getPrice());
        assertEquals("image2.jpg", tempList.get(2).getImageUrl());

        assertEquals("밀크쉐이크", tempList.get(3).getName());
        assertEquals(3000L, tempList.get(3).getPrice());
        assertEquals("image3.jpg", tempList.get(3).getImageUrl());

    }

    @Test
    @DisplayName("단일 상품 조회 기능 확인")
    void checkSelectProductById() {
        //given
        Product tempProduct1 = new Product(1L,"콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product(2L,"레모네이드", 2500L, "image2.jpg");
        Product selectedProduct1;
        Product selectedProduct2;
        //when
        productService.addProduct(tempProduct1);
        productService.addProduct(tempProduct2);
        selectedProduct1 = productService.getProductById(1L);
        selectedProduct2 = productService.getProductById(2L);
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
        Product tempProduct1 = new Product(1111L,"콜라", 1500L, "image1.jpg");
        Long selecteLong;
        //when
        productService.addProduct(tempProduct1);
        productService.deleteProduct(1L);

        //then
        assertThrows(IllegalArgumentException.class,
            () -> productService.deleteProduct(2L));
        assertEquals(0, productService.getAllProducts().size());
    }

    @Test
    @DisplayName("상품 수정 기능 확인")
    void checkUpdateProduct() {
        //given
        Product tempProduct1 = new Product(1111L,"콜라", 1500L, "image1.jpg");
        Product tempProduct2 = new Product(2222L,"아이스크림", 1000L, "image1.jpg");
        //when
        productService.addProduct(tempProduct1);
        productService.updateProduct(1L, tempProduct2);
        //then
        assertEquals("아이스크림", productService.getProductById(1L).getName());
        assertEquals(1000L, productService.getProductById(1L).getPrice());
        assertThrows(IllegalArgumentException.class,
            () -> productService.updateProduct(2L, tempProduct2));
        assertEquals(1, productService.getAllProducts().size());
    }
}
