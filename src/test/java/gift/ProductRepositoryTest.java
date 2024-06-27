package gift;


import gift.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gift.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

  private ProductRepository productRepository;

  @BeforeEach
  public void setUp() {
    productRepository = new ProductRepository();
  }

  @Test
  public void testFindAll() {
    Product product1 = new Product();
    product1.setName("Product 1");
    product1.setPrice(1000);
    product1.setImageUrl("http://example.com/product1.jpg");

    Product product2 = new Product();
    product2.setName("Product 2");
    product2.setPrice(2000);
    product2.setImageUrl("http://example.com/product2.jpg");

    productRepository.save(product1);
    productRepository.save(product2);

    List<Product> products = productRepository.findAll();
    assertEquals(2, products.size());
  }

  @Test
  public void testFindById() {
    Product product = new Product();
    product.setName("Product 1");
    product.setPrice(1000);
    product.setImageUrl("http://example.com/product1.jpg");

    Product savedProduct = productRepository.save(product);
    Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

    assertTrue(foundProduct.isPresent());
    assertEquals(savedProduct.getId(), foundProduct.get().getId());
  }

  @Test
  public void testSave() {
    Product product = new Product();
    product.setName("Product 1");
    product.setPrice(1000);
    product.setImageUrl("http://example.com/product1.jpg");

    Product savedProduct = productRepository.save(product);

    assertNotNull(savedProduct.getId());
    assertEquals(product.getName(), savedProduct.getName());
  }

  @Test
  public void testDeleteById() {
    Product product = new Product();
    product.setName("Product 1");
    product.setPrice(1000);
    product.setImageUrl("http://example.com/product1.jpg");

    Product savedProduct = productRepository.save(product);
    productRepository.deleteById(savedProduct.getId());

    Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
    assertFalse(foundProduct.isPresent());
  }
}
