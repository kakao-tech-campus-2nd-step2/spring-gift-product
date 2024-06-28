package gift.repository;

import gift.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductMemoryRepositoryTest {
    private ProductMemoryRepository repository;

    @BeforeEach
    void setup() {
        repository = new ProductMemoryRepository();
    }

    @Test
    void save(){
        Product product = new Product();
        product.setName("Test Product");

        Product savedProduct = repository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());

    }

    @Test
    void findById(){
        Product product = new Product();
        product.setName("Test Product");

        Product savedProduct = repository.save(product);
        Optional<Product> foundProduct = repository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct.getId(), foundProduct.get().getId());
    }

    @Test
    void findByName(){
        Product product = new Product();
        product.setName("Test Product");

        repository.save(product);
        Optional<Product> foundProduct = repository.findByName("Test Product");

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
    }

    @Test
    void findAll() {
        Product product1 = new Product();
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setName("Product 2");

        repository.save(product1);
        repository.save(product2);

        List<Product> products = repository.findAll();

        assertEquals(2, products.size());
    }

    @Test
    void deleteById() {
        Product product = new Product();
        product.setName("Test Product");

        Product savedProduct = repository.save(product);
        Optional<Product> deletedProduct = repository.deleteById(savedProduct.getId());

        assertTrue(deletedProduct.isPresent());
        assertEquals(savedProduct.getId(), deletedProduct.get().getId());
        assertFalse(repository.findById(savedProduct.getId()).isPresent());
    }

}
