package gift.repository;

import gift.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@JdbcTest
@Import(ProductRepository.class)
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CREATE TABLE kakaoProduct (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price INT, image_url VARCHAR(255))");
    }

    @Test
    public void testSaveAndFindById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);
        product.setImageUrl("http://example.com/image.jpg");

        productRepository.save(product);
        List<Product> products = productRepository.findAll();
        Product savedProduct = products.get(0);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(100, savedProduct.getPrice());
        assertEquals("http://example.com/image.jpg", savedProduct.getImageUrl());

        Product foundProduct = productRepository.findById(savedProduct.getId());
        assertEquals(savedProduct.getId(), foundProduct.getId());
        assertEquals(savedProduct.getName(), foundProduct.getName());
        assertEquals(savedProduct.getPrice(), foundProduct.getPrice());
        assertEquals(savedProduct.getImageUrl(), foundProduct.getImageUrl());
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(100);
        product1.setImageUrl("http://example.com/image1.jpg");

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(200);
        product2.setImageUrl("http://example.com/image2.jpg");

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setName("Original Name");
        product.setPrice(100);
        product.setImageUrl("http://example.com/image.jpg");

        productRepository.save(product);
        List<Product> products = productRepository.findAll();
        Product savedProduct = products.get(0);

        savedProduct.setName("Updated Name");
        savedProduct.setPrice(150);
        savedProduct.setImageUrl("http://example.com/updated_image.jpg");

        productRepository.update(savedProduct);
        Product updatedProduct = productRepository.findById(savedProduct.getId());

        assertEquals("Updated Name", updatedProduct.getName());
        assertEquals(150, updatedProduct.getPrice());
        assertEquals("http://example.com/updated_image.jpg", updatedProduct.getImageUrl());
    }

    @Test
    public void testDeleteById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);
        product.setImageUrl("http://example.com/image.jpg");

        productRepository.save(product);
        List<Product> products = productRepository.findAll();
        Product savedProduct = products.get(0);

        productRepository.deleteById(savedProduct.getId());
        List<Product> remainingProducts = productRepository.findAll();
        assertTrue(remainingProducts.isEmpty());
    }
}
