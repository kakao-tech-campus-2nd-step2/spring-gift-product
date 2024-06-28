package gift.service;

import gift.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void getAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals("아이스 카페 아메리카노 T", products.get(0).getName());
    }

    @Test
    void addProduct() {
        Product newProduct = new Product.Builder()
                .id(2L)
                .name("새 상품")
                .price(1000)
                .imageUrl("https://example.com/newproduct.jpg")
                .build();
        productService.addProduct(newProduct);

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertTrue(products.stream().anyMatch(product -> "새 상품".equals(product.getName())));
    }

    @Test
    void updateProduct() {
        Product updatedProduct = new Product.Builder()
                .id(8146027L)
                .name("업데이트된 상품")
                .price(5000)
                .imageUrl("https://example.com/updatedproduct.jpg")
                .build();
        productService.updateProduct(8146027L, updatedProduct);

        Product product = productService.getAllProducts().stream()
                .filter(p -> p.getId().equals(8146027L))
                .findFirst()
                .orElse(null);

        assertNotNull(product);
        assertEquals("업데이트된 상품", product.getName());
        assertEquals(5000, product.getPrice());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(8146027L);

        List<Product> products = productService.getAllProducts();
        assertTrue(products.isEmpty());
    }
}
