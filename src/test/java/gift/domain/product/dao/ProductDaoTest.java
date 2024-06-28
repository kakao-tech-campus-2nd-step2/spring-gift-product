package gift.domain.product.dao;

import static org.assertj.core.api.Assertions.*;

import gift.domain.product.entity.Product;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    @DisplayName("DB 상품 추가")
    void insert() {
        // given
        Product product = new Product(null, "탕종 블루베리 베이글", 3500, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg");

        // when
        Product savedProduct = productDao.insert(product);

        // then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(savedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }

    /**
     * 리스트 인덱스 번호 때문에 개별 실행해야 함.
     */
    @Test
    @DisplayName("DB 전체 상품 조회")
    void findAll() {
        // given
        Product product1 = new Product(null, "탕종 블루베리 베이글", 3500, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg");
        Product product2 = new Product(null, "오트 콜드 브루", 5800, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003285]_20210416154437069.jpg");
        productDao.insert(product1);
        productDao.insert(product2);

        // when
        List<Product> productList = productDao.findAll();

        // then
        assertThat(productList.get(3)).isNotNull();
        assertThat(productList.get(3).getId()).isNotNull();
        assertThat(productList.get(3).getName()).isEqualTo(product1.getName());
        assertThat(productList.get(3).getPrice()).isEqualTo(product1.getPrice());
        assertThat(productList.get(3).getImageUrl()).isEqualTo(product1.getImageUrl());
        assertThat(productList.get(4)).isNotNull();
        assertThat(productList.get(4).getId()).isNotNull();
        assertThat(productList.get(4).getName()).isEqualTo(product2.getName());
        assertThat(productList.get(4).getPrice()).isEqualTo(product2.getPrice());
        assertThat(productList.get(4).getImageUrl()).isEqualTo(product2.getImageUrl());
    }

    @Test
    @DisplayName("DB 특정 상품 조회")
    void findById() {
        // given
        Product product = new Product(null, "탕종 블루베리 베이글", 3500, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg");
        Product savedProduct = productDao.insert(product);

        // when
        Product foundProduct = productDao.findById(savedProduct.getId()).get();

        // then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isNotNull();
        assertThat(foundProduct.getName()).isEqualTo(product.getName());
        assertThat(foundProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(foundProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }


    @Test
    @DisplayName("DB 상품 수정")
    void update() {
        // given
        Product product1 = new Product(null, "탕종 블루베리 베이글", 3500, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg");
        Product savedProduct = productDao.insert(product1);
        Product product2 = new Product(savedProduct.getId(), "오트 콜드 브루", 5800, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003285]_20210416154437069.jpg");

        // when
        Product updatedProduct = productDao.update(product2).get();

        // then
        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(updatedProduct.getName()).isEqualTo(product2.getName());
        assertThat(updatedProduct.getPrice()).isEqualTo(product2.getPrice());
        assertThat(updatedProduct.getImageUrl()).isEqualTo(product2.getImageUrl());
    }
}