package gift.service;

import gift.controller.ProductDto;
import gift.domain.Product;
import gift.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setPrice(100);
        productDto.setImageUrl("http://example.com/image.jpg");

        Product product = Product.dtoToEntity(productDto);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product registeredProduct = productService.register(productDto);

        assertEquals("Test Product", registeredProduct.getName());
        assertEquals(100, registeredProduct.getPrice());
        assertEquals("http://example.com/image.jpg", registeredProduct.getImageUrl());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void register_throwsException_whenProductExists() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");

        when(productRepository.findByName("Test Product")).thenReturn(Optional.of(new Product()));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            productService.register(productDto);
        });

        assertEquals("이미 존재하는 상품입니다.", exception.getMessage());
    }

    @Test
    void findProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setName("Product 2");

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.findProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findOne() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findOne(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void update() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Updated Product");
        productDto.setPrice(200);
        productDto.setImageUrl("http://example.com/updated-image.jpg");

        Product product = new Product();
        product.setId(1L);
        product.setName("Old Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> updatedProduct = productService.update(1L, productDto);

        assertTrue(updatedProduct.isPresent());
        assertEquals("Updated Product", updatedProduct.get().getName());
        assertEquals(200, updatedProduct.get().getPrice());
        assertEquals("http://example.com/updated-image.jpg", updatedProduct.get().getImageUrl());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.deleteById(1L)).thenReturn(Optional.of(product));

        Optional<Product> deletedProduct = productService.delete(1L);

        assertTrue(deletedProduct.isPresent());
        assertEquals("Test Product", deletedProduct.get().getName());
        verify(productRepository, times(1)).deleteById(1L);
    }
}
