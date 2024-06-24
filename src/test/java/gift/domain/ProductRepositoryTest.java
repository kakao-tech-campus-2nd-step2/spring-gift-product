package gift.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gift.controller.request.ProductRequest;
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

    @DisplayName("상품 정보를 수정한다.")
    @Test
    void edit() throws Exception {
        //given
        Product product = new Product("아이스 아메리카노", 3500, "https://examle.com");
        Product savedProduct = productRepository.save(product);

        Long productId = savedProduct.getId();
        ProductRequest request = new ProductRequest("망고 스무디", 5000, "https://test.com");

        //when
        productRepository.edit(productId, request.toEntity());

        //then
        assertThat(savedProduct.getName()).isEqualTo(request.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(request.getPrice());
        assertThat(savedProduct.getImageUrl()).isEqualTo(request.getImageUrl());
    }

    @DisplayName("상품 아이디를 받아, 해당하는 상품을 삭제한다.")
    @Test
    void deleteById() throws Exception {
        //given
        Product product = new Product("아이스 아메리카노", 3500, "https://examle.com");
        Product savedProduct = productRepository.save(product);

        Long productId = savedProduct.getId();
        int prevSize = productRepository.findAll().size();

        //when
        productRepository.deleteById(productId);

        //then
        int currSize = productRepository.findAll().size();

        assertThat(currSize).isEqualTo(prevSize - 1);
    }

}
