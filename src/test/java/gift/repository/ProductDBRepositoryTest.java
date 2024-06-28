package gift.repository;

import gift.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductDBRepositoryTest {

    @Autowired
    ProductDBRepository productDBRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void before(){
        jdbcTemplate.execute("DROP TABLE product IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE product(id SERIAL, name VARCHAR(255),price int ,imageUrl VARCHAR(255))");
    }

    @Test
    @DisplayName("저장 테스트")
    void 저장_테스트(){
        //given
        Product product = new Product("테스트1", 1000, "abc.png");

        //when
        productDBRepository.save(product);
        Product findProduct = productDBRepository.findById(1L).orElseThrow();

        //then
        assertThat(product.getName()).isEqualTo(findProduct.getName());
        assertThat(product.getPrice()).isEqualTo(findProduct.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(findProduct.getImageUrl());
    }

    @Test
    @DisplayName("조회 테스트")
    void 조회_테스트(){
        //given
        Product product = new Product("테스트1", 1000, "abc.png");

        //when
        productDBRepository.save(product);
        Product findProduct = productDBRepository.findById(1L).orElseThrow();
        Optional<Product> findProduct2 = productDBRepository.findById(2L);

        //then
        assertThat(product.getName()).isEqualTo(findProduct.getName());
        assertThat(product.getPrice()).isEqualTo(findProduct.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(findProduct.getImageUrl());
        assertThat(findProduct2).isEmpty();
    }

    @Test
    @DisplayName("전체_조회 테스트")
    void 전체_조회_테스트(){
        //given
        Product product1 = new Product("테스트1", 1000, "abc.png");
        Product product2 = new Product("테스트2", 1000, "abc.png");
        Product product3 = new Product("테스트3", 1000, "abc.png");

        //when
        productDBRepository.save(product1);
        productDBRepository.save(product2);
        productDBRepository.save(product3);
        List<Product> products = productDBRepository.findAll();

        //then
        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수정 테스트")
    void 수정_테스트(){
        //given
        Product product = new Product("테스트1", 1000, "abc.png");

        //when
        productDBRepository.save(product);
        Long updatedRow = productDBRepository.update(1L, 2000);
        Product findProduct = productDBRepository.findById(1L).orElseThrow();
        //then
        assertThat(updatedRow).isEqualTo(1);
        assertThat(findProduct.getName()).isEqualTo(product.getName());
        assertThat(findProduct.getImageUrl()).isEqualTo(product.getImageUrl());
        assertThat(findProduct.getPrice()).isEqualTo(2000);
    }


    @Test
    @DisplayName("삭제 테스트")
    void 삭제_테스트(){
        //given
        Product product = new Product("테스트1", 1000, "abc.png");

        //when
        productDBRepository.save(product);
        Long deletedRow = productDBRepository.delete(1L);
        List<Product> products = productDBRepository.findAll();

        //then
        assertThat(deletedRow).isEqualTo(1);
        assertThat(products.size()).isEqualTo(0);
    }


}
