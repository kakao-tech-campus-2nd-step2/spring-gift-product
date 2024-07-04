package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("모든 제품 조회")
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", 100, "http://example.com/image1");
        Product product2 = new Product(2L, "Product2", 200, "http://example.com/image2");
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        List<Product> products = response.getBody();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("ID로 특정 제품 조회")
    void testGetProductById() {
        Product product = new Product(1L, "Product1", 100, "http://example.com/image1");
        when(productService.getProductById(anyLong())).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Product1", response.getBody().getName());
        assertEquals(100, response.getBody().getPrice());
        assertEquals("http://example.com/image1", response.getBody().getImageUrl());
    }

    @Test
    @DisplayName("새로운 상품 생성")
    void testCreateProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        when(productService.createProduct(any(Product.class))).thenReturn(true);

        ResponseEntity<Map<String, Object>> response = productController.createProduct(product);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product created successfully.", response.getBody().get("message"));
        assertEquals(product, response.getBody().get("product"));
    }

    @Test
    @DisplayName("기존 상품을 수정")
    void testUpdateProduct() {
        Product updatedProduct = new Product(1L, "UpdatedProduct1", 150, "http://example.com/updated_image1");
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(true);
        when(productService.getProductById(anyLong())).thenReturn(updatedProduct);

        ResponseEntity<Map<String, Object>> response = productController.updateProduct(1L, updatedProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product updated successfully.", response.getBody().get("message"));
        assertEquals(updatedProduct, response.getBody().get("product"));
    }

    @Test
    @DisplayName("기존 제품 삭제")
    void testDeleteProduct() {
        when(productService.deleteProduct(anyLong())).thenReturn(true);

        ResponseEntity<Map<String, Object>> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
