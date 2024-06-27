package gift.product;

import static org.assertj.core.api.Assertions.*;

import gift.product.repository.MemoryProductRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductMemoryRepositoryTest {
    private MemoryProductRepository memoryProductRepository;

    @BeforeEach
    public void setUp() {
        memoryProductRepository = new MemoryProductRepository();
    }

    @Test
    void 모든_상품_추가_이후_조회() {
        // given
        memoryProductRepository.createProduct(new Product(0L, "productA", 1000, "imageUrlA"));
        memoryProductRepository.createProduct(new Product(0L, "productB", 2000, "imageUrlB"));

        // when
        List<Product> allProducts = memoryProductRepository.findAllProducts();

        // then
        assertThat(allProducts.size()).isEqualTo(2L);
    }
    
    @Test
    void 상품_정보_수정() {
        // given
        memoryProductRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        Product requestBody = new Product(0L, "productC", 0, null);
        Product updatedProduct = memoryProductRepository.updateProduct(1L, requestBody);

        // then
        assertThat(requestBody.getName()).isEqualTo(updatedProduct.getName());
    }

    @Test
    void 상품_삭제() {
        // given
        memoryProductRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        memoryProductRepository.deleteProduct(1L);

        // then
        assertThat(memoryProductRepository.findAllProducts().size()).isEqualTo(0);
    }
}
