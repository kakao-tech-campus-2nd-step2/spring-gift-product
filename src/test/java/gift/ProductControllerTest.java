package gift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {

    private ProductController productController;

    @BeforeEach
    public void setUp() {
        productController = new ProductController();
    }

    @Test
    public void 상품_추가() {
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "아이스 카페 아메리카노 T");
        productData.put("price", 4500);
        productData.put("imageUrl", "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        Product addedProduct = productController.addProduct(productData);

        assertNotNull(addedProduct);
        assertEquals(1L, addedProduct.id);
        assertEquals("아이스 카페 아메리카노 T", addedProduct.name);
        assertEquals(4500, addedProduct.price);
        assertEquals("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg", addedProduct.imageUrl);
    }
}
