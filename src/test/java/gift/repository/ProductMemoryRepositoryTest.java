package gift.repository;

import gift.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProductMemoryRepositoryTest {

    private final ProductMemoryRepository productRepository = new ProductMemoryRepository();

    @BeforeEach
    void beforeEach(){
        productRepository.clear();
    }

    @Test
    @DisplayName("상품 저장 테스트")
    void 상품_저장_테스트(){
        //given
        Product product = new Product("테스트 상품", 1000, "abc.png");

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertThat(product.getId()).isEqualTo(savedProduct.getId());
    }

    @Test
    @DisplayName("단품 조회")
    void 단품_조회_테스트(){
        //given
        Product product = new Product("테스트 상품1", 1000, "abc.png");

        Product savedProduct = productRepository.save(product);

        //when
        Product findProduct = productRepository.findById(savedProduct.getId()).orElseThrow();
        Optional<Product> nullProduct = productRepository.findById(savedProduct.getId() + 1);

        //then
        assertThat(findProduct.getId()).isEqualTo(product.getId());
        assertThat(findProduct.getName()).isEqualTo(product.getName());
        assertThat(nullProduct).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("전체 상품 조회")
    void 전체_상품_조회_테스트(){
        //given
        Product product1 = new Product("테스트 상품1", 1000, "abc.png");
        Product product2 = new Product("테스트 상품2", 1000, "abc.png");
        Product product3 = new Product("테스트 상품3", 1000, "abc.png");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //when
        List<Product> products = productRepository.findAll();

        //then
        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void 상품_삭제_테스트(){
        //given
        Product product1 = new Product("테스트 상품1", 1000, "abc.png");
        Product product2 = new Product("테스트 상품2", 1000, "abc.png");
        Product product3 = new Product("테스트 상품3", 1000, "abc.png");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //when
        Long deletedId = productRepository.delete(product1.getId());
        List<Product> products = productRepository.findAll();

        //then
        assertThat(deletedId).isEqualTo(product1.getId());
        assertThat(products.size()).isEqualTo(2);
    }

}