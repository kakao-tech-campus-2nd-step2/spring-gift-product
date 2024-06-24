package gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void before() {
        productRepository.clear();
    }

    @DisplayName("모든 상품 정보를 조회한다.")
    @Test
    void findAll() throws Exception {
        //when
        List<Product> products = productRepository.findAll();

        //then
        assertThat(products.size()).isEqualTo(0);
    }

    @DisplayName("상품 하나를 추가한다.")
    @Test
    void save() throws Exception {
        //given
        Product product = new Product();

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertThat(savedProduct).isNotNull();
    }

}
