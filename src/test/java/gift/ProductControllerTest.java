package gift;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductControllerTest {
    private final ProductController controller = new ProductController();

    @Test
    @DisplayName("상품이 정상적으로 생성되는지 확인")
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(1000);
        product.setImageUrl("https://example.com/image.jpg");

        Product addedProduct = controller.addProduct(product);
        assertEquals(addedProduct.getId(), 1L);
        assertEquals(addedProduct.getName(), "Test Product");
        assertEquals(addedProduct.getPrice(), 1000);
        assertEquals(addedProduct.getImageUrl(), "https://example.com/image.jpg");
    }

}
