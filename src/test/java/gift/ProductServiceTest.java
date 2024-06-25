package gift;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}