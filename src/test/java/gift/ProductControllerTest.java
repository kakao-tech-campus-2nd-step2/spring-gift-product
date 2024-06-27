package gift;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {
    private ProductController controller;

    @BeforeEach
    public void runBeforeTest() {
        controller = new ProductController();
        Map<Long, Product> products = new HashMap<>();

        Product product = new Product(1L, "카카오 카페모카", 5200, "http://imageishere.com");
        products.put(1L, product);
        controller.addProduct(product);
    }

    @Test
    @DisplayName("상품이 정상적으로 생성되는지 확인")
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(1000);
        product.setImageUrl("https://example.com/image.jpg");

        Product addedProduct = controller.addProduct(product);
        assertEquals(addedProduct.getId(), 2L);
        assertEquals(addedProduct.getName(), "Test Product");
        assertEquals(addedProduct.getPrice(), 1000);
        assertEquals(addedProduct.getImageUrl(), "https://example.com/image.jpg");
    }

    @Test
    @DisplayName("전체 상품이 정상적으로 조회되는지 확인")
    public void testGetAllProducts() {
        List<Product> productList = controller.getAllProducts();

        Product product = productList.get(0);
        assertEquals(1, productList.size());
        assertEquals(product.getId(), 1L);
        assertEquals(product.getName(), "카카오 카페모카");
        assertEquals(product.getPrice(), 5200);
        assertEquals(product.getImageUrl(), "http://imageishere.com");
    }

    @Test
    @DisplayName("상품이 정상적으로 삭제되는지 확인")
    public void deleteProduct() {
        boolean isDeleted = controller.deleteProduct(1L);

        assertTrue(isDeleted);
    }
}
