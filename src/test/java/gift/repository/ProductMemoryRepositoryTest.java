package gift.repository;

import gift.controller.ProductDto;
import gift.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductMemoryRepositoryTest {

    private ProductMemoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductMemoryRepository();
    }

    @Test
    void saveProduct() {
        Product product = new Product("Test Product", 100, "test-url");

        Product savedProduct = repository.save(product);

        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getImageUrl(), savedProduct.getImageUrl());
        assertTrue(savedProduct.getId() > 0);
    }

    @Test
    void findById_id있을때() {
        Product product = new Product("Test Product", 100, "test-url");
        Product savedProduct = repository.save(product);
        Long productId = savedProduct.getId();

        Optional<Product> foundProduct = repository.findById(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct, foundProduct.get());
    }

    @Test
    void findById_id없을때() {
        Optional<Product> foundProduct = repository.findById(999L);

        assertFalse(foundProduct.isPresent());
    }

    @Test
    void findByName_name있을때() {
        Product product = new Product("Test Product", 100, "test-url");
        repository.save(product);
        String productName = product.getName();

        Optional<Product> foundProduct = repository.findByName(productName);

        assertTrue(foundProduct.isPresent());
        assertEquals(productName, foundProduct.get().getName());
    }

    @Test
    void findByName_name없을때() {
        Optional<Product> foundProduct = repository.findByName("Non Existing Product");

        assertFalse(foundProduct.isPresent());
    }

    @Test
    void findAll() {
        Product product1 = new Product("Product 1", 100, "url-1");
        Product product2 = new Product("Product 2", 200, "url-2");
        repository.save(product1);
        repository.save(product2);

        List<Product> allProducts = repository.findAll();

        assertEquals(2, allProducts.size());
    }

    @Test
    void updateById_id있을때() {
        Product product = new Product("Initial Product", 100, "initial-url");
        Product savedProduct = repository.save(product);
        Long productId = savedProduct.getId();
        ProductDto updatedProductDto = new ProductDto("Updated Product", 200, "updated-url");

        Optional<Product> updatedProductOptional = repository.updateById(productId, updatedProductDto);

        assertTrue(updatedProductOptional.isPresent());
        Product updatedProduct = updatedProductOptional.get();
        assertEquals(updatedProductDto.getName(), updatedProduct.getName());
        assertEquals(updatedProductDto.getPrice(), updatedProduct.getPrice());
        assertEquals(updatedProductDto.getImageUrl(), updatedProduct.getImageUrl());
    }

    @Test
    void updateById_id없을때() {
        Optional<Product> updatedProductOptional = repository.updateById(999L, new ProductDto("Updated Product", 200, "updated-url"));

        assertFalse(updatedProductOptional.isPresent());
    }

    @Test
    void deleteById_id있을때() {
        Product product = new Product("To Be Deleted", 100, "delete-me-url");
        Product savedProduct = repository.save(product);
        Long productId = savedProduct.getId();

        Optional<Product> deletedProductOptional = repository.deleteById(productId);

        assertTrue(deletedProductOptional.isPresent());
        assertEquals(product, deletedProductOptional.get());
        assertFalse(repository.findById(productId).isPresent());
    }

    @Test
    void deleteById_id없을때() {
        Optional<Product> deletedProductOptional = repository.deleteById(999L);

        assertFalse(deletedProductOptional.isPresent());
    }
}