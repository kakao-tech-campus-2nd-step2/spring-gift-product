package gift.product.application;

import gift.product.domain.Product;
import gift.product.domain.ProductMemoryRepository;
import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;
import gift.product.domain.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void endUp() {
        jdbcTemplate.execute("TRUNCATE TABLE product");
    }

    @Test
    public void 모든_상품_조회_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");
        productService.add(createCommand);

        // When
        List<ProductResponse> products = productService.findAll();

        // Then
        assertThat(products).hasSize(1);
        assertThat(products.get(0).name()).isEqualTo("Product1");
    }

    @Test
    public void 상품_ID로_조회_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");
        productService.add(createCommand);
        Long productId = productRepository.findAll().get(0).getId();

        // When
        ProductResponse productResponse = productService.findById(productId);

        // Then
        assertThat(productResponse.name()).isEqualTo("Product1");
    }

    @Test
    public void 상품_ID로_조회_실패_테스트() {
        // Given
        Long productId = 1L;

        // When / Then
        assertThatThrownBy(() -> productService.findById(productId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found");
    }

    @Test
    public void 상품_추가_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");

        // When
        productService.add(createCommand);

        // Then
        assertThat(productRepository.findAll()).hasSize(1);
        assertThat(productRepository.findAll().get(0).getName()).isEqualTo("Product1");
    }

    @Test
    public void 상품_업데이트_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");
        productService.add(createCommand);
        Long productId = productRepository.findAll().get(0).getId();

        ProductUpdateCommand updateCommand = new ProductUpdateCommand(productId, "UpdatedProduct", 2000, "http://example.com/image2.jpg");

        // When
        productService.update(updateCommand);

        // Then
        Product updatedProduct = productRepository.findById(productId).get();
        assertThat(updatedProduct.getName()).isEqualTo("UpdatedProduct");
        assertThat(updatedProduct.getPrice()).isEqualTo(2000);
        assertThat(updatedProduct.getImageUrl()).isEqualTo("http://example.com/image2.jpg");
    }

    @Test
    public void 상품_업데이트_실패_테스트() {
        // Given
        Long productId = 1L;
        ProductUpdateCommand updateCommand = new ProductUpdateCommand(productId, "UpdatedProduct", 2000, "http://example.com/image2.jpg");

        // When / Then
        assertThatThrownBy(() -> productService.update(updateCommand))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found");
    }

    @Test
    public void 상품_삭제_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");
        productService.add(createCommand);
        Long productId = productRepository.findAll().get(0).getId();

        // When
        productService.delete(productId);

        // Then
        assertThat(productRepository.findById(productId)).isEmpty();
    }
}
