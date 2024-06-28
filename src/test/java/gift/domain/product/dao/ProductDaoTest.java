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

    @Test
    @DisplayName("DB 전체 상품 조회")
    void findAll() {
        // given
        Product product1 = new Product(null, "탕종 블루베리 베이글", 3500, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004823]_20230911131337469.jpg");
        Product product2 = new Product(null, "오트 콜드 브루", 5800, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003285]_20210416154437069.jpg");
        Product savedProduct1 = productDao.insert(product1);
        Product savedProduct2 = productDao.insert(product2);

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
}