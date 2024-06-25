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

    @Test
    @DisplayName("getProductById exception test")
    void getProductByIdExceptionTest() throws Exception {
        //given
        ProductService productService = new ProductService();
        Product product1 = new Product(1L, "product1", 10000, null);
        Product product2 = new Product(2L, "product2", 20000, null);
        productService.addProduct(product1);
        productService.addProduct(product2);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(3L));
    }

    @Test
    @DisplayName("getProductById test")
    void getProductByIdTest() throws Exception {
        //given
        ProductService productService = new ProductService();
        Product product1 = new Product(1L, "product1", 10000, null);
        Product product2 = new Product(2L, "product2", 20000, null);
        productService.addProduct(product1);
        productService.addProduct(product2);

        //when
        Product product = productService.getProductById(2L);

        //then
        assertThat(product).isEqualTo(product2);
    }

    @Test
    @DisplayName("updateProduct test")
    void updateProductTest() throws Exception {
        //given
        ProductService productService = new ProductService();
        Product product1 = new Product(1L, "product1", 10000, null);
        Product product2 = new Product(2L, "product2", 20000, null);
        Product newProduct = new Product(1L, "product3", 30000, null);

        productService.addProduct(product1);
        productService.addProduct(product2);


        //when
        productService.updateProduct(newProduct);
        Product updatedProduct = productService.getProductById(1L);

        //then
        assertThat(updatedProduct).isEqualTo(newProduct);
    }
}