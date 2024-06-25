package gift;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

    @Test
    @DisplayName("getAllProducts empty test")
    void getAllProductsEmptyTest() throws Exception {
        //given
        ProductService productService = new ProductService();

        //when
        List<Product> products = productService.getAllProducts();

        //then
        assertThat(products).isNull();
    }

    @Test
    @DisplayName("getAllProducts test")
    void getAllProductsTest() throws Exception {
        //given
        ProductService productService = new ProductService();
        Product product1 = new Product(1L, "product1", 10000, null);
        Product product2 = new Product(2L, "product2", 20000, null);
        productService.addProduct(product1);
        productService.addProduct(product2);
        List<Product> expected = List.of(product1, product2);

        //when
        List<Product> products = productService.getAllProducts();

        //then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products).containsAll(expected);
    }
}