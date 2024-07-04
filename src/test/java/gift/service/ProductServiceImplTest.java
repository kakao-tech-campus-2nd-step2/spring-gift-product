package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("모든 제품 조회")
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", 100, "http://example.com/image1");
        Product product2 = new Product(2L, "Product2", 200, "http://example.com/image2");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    @DisplayName("ID로 특정 제품 조회")
    void testGetProductById() {
        Product product = new Product(1L, "Product1", 100, "http://example.com/image1");
        when(productRepository.findById(anyLong())).thenReturn(product);

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
        when(productRepository.save(any(Product.class))).thenReturn(true);

        boolean success = productService.createProduct(product);
        assertTrue(success);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("기존 상품을 수정")
    void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Product1", 100, "http://example.com/image1");
        when(productRepository.findById(anyLong())).thenReturn(existingProduct);
        when(productRepository.update(any(Product.class))).thenReturn(true);

        Product updatedProduct = new Product(1L, "UpdatedProduct1", 150, "http://example.com/updated_image1");
        boolean success = productService.updateProduct(1L, updatedProduct);

        assertTrue(success);
        verify(productRepository, times(1)).update(existingProduct);
    }

    @Test
    @DisplayName("기존 제품 삭제")
    void testDeleteProduct() {
        when(productRepository.delete(anyLong())).thenReturn(true);

        boolean success = productService.deleteProduct(1L);
        assertTrue(success);
        verify(productRepository, times(1)).delete(1L);
    }
}
