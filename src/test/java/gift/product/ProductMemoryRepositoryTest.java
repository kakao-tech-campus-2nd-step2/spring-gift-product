package gift.product;

import static org.assertj.core.api.Assertions.*;

import gift.product.repository.ProductMemoryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductMemoryRepositoryTest {
    private ProductMemoryRepository productMemoryRepository;

    @BeforeEach
    public void setUp() {
        productMemoryRepository = new ProductMemoryRepository();
    }

    @Test
    void 모든_상품_추가_이후_조회() {
        // given
        productMemoryRepository.createProduct(new Product(0L, "productA", 1000, "imageUrlA"));
        productMemoryRepository.createProduct(new Product(0L, "productB", 2000, "imageUrlB"));

        // when
        List<Product> allProducts = productMemoryRepository.findAllProducts();

        // then
        assertThat(allProducts.size()).isEqualTo(2L);
    }
    
    @Test
    void 상품_정보_수정() {
        // given
        productMemoryRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        Product requestBody = new Product(0L, "productC", 0, null);
        Product updatedProduct = productMemoryRepository.updateProduct(1L, requestBody);

        // then
        assertThat(requestBody.getName()).isEqualTo(updatedProduct.getName());
    }

    @Test
    void 상품_삭제() {
        // given
        productMemoryRepository.createProduct(new Product(1L, "productA", 1000, "imageUrlA"));

        // when
        productMemoryRepository.deleteProduct(1L);

        // then
        assertThat(productMemoryRepository.findAllProducts().size()).isEqualTo(0);
    }
}
