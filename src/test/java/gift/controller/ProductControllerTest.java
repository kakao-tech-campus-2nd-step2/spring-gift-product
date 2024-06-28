package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import gift.service.ProductServiceImpl;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProductController productController;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepositoryImpl(jdbcTemplate);
        productService = new ProductServiceImpl(productRepository);
        productController = new ProductController(productService);
    }

    @Test
    @DisplayName("모든 제품 조회")
    void testGetAllProducts() {
        Product product1 = new Product(null, "Product1", 100, "http://example.com/image1");
        Product product2 = new Product(null, "Product2", 200, "http://example.com/image2");
        productService.createProduct(product1);
        productService.createProduct(product2);

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        List<Product> products = response.getBody();

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("ID로 특정 제품 조회")
    void testGetProductById() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

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

        ResponseEntity<Product> response = productController.createProduct(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Product1", response.getBody().getName());
        assertEquals(100, response.getBody().getPrice());
        assertEquals("http://example.com/image1", response.getBody().getImageUrl());
    }

    @Test
    @DisplayName("기존 상품을 수정")
    void testUpdateProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        Product updatedProduct = new Product(1L, "UpdatedProduct1", 150, "http://example.com/updated_image1");
        ResponseEntity<Product> response = productController.updateProduct(1L, updatedProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UpdatedProduct1", response.getBody().getName());
        assertEquals(150, response.getBody().getPrice());
        assertEquals("http://example.com/updated_image1", response.getBody().getImageUrl());
    }

    @Test
    @DisplayName("기존 제품 삭제")
    void testDeleteProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        ResponseEntity<Void> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Product foundProduct = productService.getProductById(1L);
        assertNull(foundProduct);
    }
}
