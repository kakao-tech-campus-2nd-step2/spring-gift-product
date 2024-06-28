package gift.service;

import gift.controller.ProductDto;
import gift.domain.Product;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void register() {
        ProductDto productDto = new ProductDto("New Product", 100L, "new-product-url");
        Product product = new Product(1L, productDto.getName(), productDto.getPrice(), productDto.getImageUrl());

        when(productRepository.findByName(productDto.getName()))
                .thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class)))
                .thenReturn(product);

        Product registeredProduct = productService.register(productDto);

        assertNotNull(registeredProduct);
        assertEquals(product.getName(), registeredProduct.getName());
        assertEquals(product.getPrice(), registeredProduct.getPrice());
        assertEquals(product.getImageUrl(), registeredProduct.getImageUrl());
    }

    @Test
    void register_중복() {
        ProductDto productDto = new ProductDto("Duplicate Product", 200L, "duplicate-product-url");
        Product existingProduct = new Product(1L, productDto.getName(), productDto.getPrice(), productDto.getImageUrl());

        when(productRepository.findByName(productDto.getName()))
                .thenReturn(Optional.of(existingProduct));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> productService.register(productDto));

        assertEquals("이미 존재하는 상품입니다.", exception.getMessage());
    }

    @Test
    void findProducts() {
        Product product1 = new Product(1L, "Product 1", 100L, "url-1");
        Product product2 = new Product(2L, "Product 2", 200L, "url-2");

        when(productRepository.findAll())
                .thenReturn(Arrays.asList(product1, product2));

        List<Product> foundProducts = productService.findProducts();

        assertEquals(2, foundProducts.size());
    }

    @Test
    void findOne_id존재() {
        Product product = new Product(1L, "Found Product", 150L, "found-product-url");

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findOne(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(product.getName(), foundProduct.get().getName());
        assertEquals(product.getPrice(), foundProduct.get().getPrice());
        assertEquals(product.getImageUrl(), foundProduct.get().getImageUrl());
    }

    @Test
    void findOne_id존재x() {
        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.findOne(999L);

        assertFalse(foundProduct.isPresent());
    }

    @Test
    void update_id존재() {
        Long productId = 1L;
        ProductDto updatedProductDto = new ProductDto("Updated Product", 300L, "updated-product-url");
        Product updatedProduct = new Product(productId, updatedProductDto.getName(), updatedProductDto.getPrice(), updatedProductDto.getImageUrl());

        when(productRepository.updateById(productId, updatedProductDto))
                .thenReturn(Optional.of(updatedProduct));

        Product result = productService.update(productId, updatedProductDto);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getImageUrl(), result.getImageUrl());
    }

    @Test
    void update_id존재x() {
        Long productId = 999L;
        ProductDto updatedProductDto = new ProductDto("Updated Product", 300L, "updated-product-url");

        when(productRepository.updateById(productId, updatedProductDto))
                .thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> productService.update(productId, updatedProductDto));

        assertEquals("존재하지 않는 상품입니다.", exception.getMessage());
    }

    @Test
    void delete_id존재() {
        Long productId = 1L;
        Product product = new Product(productId, "To Be Deleted", 200L, "delete-me-url");

        when(productRepository.deleteById(productId))
                .thenReturn(Optional.of(product));

        Optional<Product> result = productService.delete(productId);

        assertTrue(result.isPresent());
        assertEquals(product.getName(), result.get().getName());
        assertEquals(product.getPrice(), result.get().getPrice());
        assertEquals(product.getImageUrl(), result.get().getImageUrl());
    }

    @Test
    void delete_id존재x() {
        Long productId = 999L;

        when(productRepository.deleteById(productId))
                .thenReturn(Optional.empty());

        Optional<Product> result = productService.delete(productId);

        assertFalse(result.isPresent());
    }
}
