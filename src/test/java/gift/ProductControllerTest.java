package gift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productDao.createProductTable();

        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "오둥이 입니다만");
        productData.put("price", 29800);
        productData.put("imageUrl", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");

        Product product = new Product(1L, "오둥이 입니다만", 29800, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        when(productDao.selectAllProducts()).thenReturn(List.of(product));
        productController.addProduct(productData);
    }

    @Test
    public void 상품_추가() {
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "아이스 카페 아메리카노 T");
        productData.put("price", 4500);
        productData.put("imageUrl", "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

        Product product = new Product(2L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        when(productDao.selectAllProducts()).thenReturn(List.of(product));

        Product addedProduct = productController.addProduct(productData);

        assertNotNull(addedProduct);
        assertEquals("아이스 카페 아메리카노 T", addedProduct.name);
        assertEquals(4500, addedProduct.price);
        assertEquals("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg", addedProduct.imageUrl);
    }

    @Test
    public void 상품_조회() {
        List<Product> productList = productController.getAllProducts();

        assertNotNull(productList);
        assertEquals(1, productList.size());
        Product product = productList.get(0);
        assertEquals("오둥이 입니다만", product.name);
        assertEquals(29800, product.price);
        assertEquals("https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg", product.imageUrl);
    }

    @Test
    public void 상품_수정() {
        Map<String, Object> updatedProductData = new HashMap<>();
        updatedProductData.put("name", "오둥이 아닙니다만");
        updatedProductData.put("price", 35000);
        updatedProductData.put("imageUrl", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");

        Product updatedProduct = new Product(1L, "오둥이 아닙니다만", 35000, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");
        when(productDao.selectProduct(1L)).thenReturn(updatedProduct);

        updatedProduct = productController.updateProduct(1L, updatedProductData);

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.id);
        assertEquals("오둥이 아닙니다만", updatedProduct.name);
        assertEquals(35000, updatedProduct.price);
        assertEquals("https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg", updatedProduct.imageUrl);
    }

    @Test
    public void 상품_수정_없는상품() {
        Map<String, Object> updatedProductData = new HashMap<>();
        updatedProductData.put("name", "오둥이 아닙니다만");
        updatedProductData.put("price", 35000);
        updatedProductData.put("imageUrl", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg");

        when(productDao.selectProduct(100L)).thenReturn(null);

        Product updatedProduct = productController.updateProduct(100L, updatedProductData);

        assertNull(updatedProduct);
    }

    @Test
    public void 상품_삭제() {
        doNothing().when(productDao).deleteProduct(1L);
        when(productDao.selectProduct(1L)).thenReturn(new Product(1L, "오둥이 입니다만", 29800, "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20240405092925_4b920eaeef6a4f0eb2a5c2a434da7ec7.jpg"));
        when(productDao.selectAllProducts()).thenReturn(List.of());

        boolean result = productController.deleteProduct(1L);
        assertTrue(result);

        List<Product> productList = productController.getAllProducts();
        assertTrue(productList.isEmpty());
    }

    @Test
    public void 상품_삭제_없는상품() {
        when(productDao.selectProduct(2L)).thenReturn(null);

        boolean result = productController.deleteProduct(2L);
        assertFalse(result);
    }
}
