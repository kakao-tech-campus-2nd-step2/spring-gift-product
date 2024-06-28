package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductServiceImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepositoryImpl(jdbcTemplate);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("모든 제품 조회")
    void testGetAllProducts() {
        Product product1 = new Product(null, "Product1", 100, "http://example.com/image1");
        Product product2 = new Product(null, "Product2", 200, "http://example.com/image2");
        productService.createProduct(product1);
        productService.createProduct(product2);

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    @DisplayName("ID로 특정 제품 조회")
    void testGetProductById() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        Product foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Product1", foundProduct.getName());
        assertEquals(100, foundProduct.getPrice());
        assertEquals("http://example.com/image1", foundProduct.getImageUrl());
    }

    @Test
    @DisplayName("새로운 상품 생성")
    void testCreateProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        Product foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Product1", foundProduct.getName());
        assertEquals(100, foundProduct.getPrice());
        assertEquals("http://example.com/image1", foundProduct.getImageUrl());
    }

    @Test
    @DisplayName("기존 상품을 수정")
    void testUpdateProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        Product updatedProduct = new Product(1L, "UpdatedProduct1", 150, "http://example.com/updated_image1");
        productService.updateProduct(1L, updatedProduct);

        Product foundProduct = productService.getProductById(1L);
        assertEquals("UpdatedProduct1", foundProduct.getName());
        assertEquals(150, foundProduct.getPrice());
        assertEquals("http://example.com/updated_image1", foundProduct.getImageUrl());
    }

    @Test
    @DisplayName("기존 제품 삭제")
    void testDeleteProduct() {
        Product product = new Product(null, "Product1", 100, "http://example.com/image1");
        productService.createProduct(product);

        productService.deleteProduct(1L);

        Product foundProduct = productService.getProductById(1L);
        assertNull(foundProduct);
    }
}
