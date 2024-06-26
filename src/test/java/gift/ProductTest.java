package gift;

import gift.product.dto.ProductDTO;
import gift.product.model.Product;
import gift.product.service.ProductService;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private final ProductService productService = new ProductService();

    @AfterEach
    public void productsInit() {
        List<Product> products = productService.getProductAll();
        for (Product product : products) {
            productService.deleteProduct(product.getId());
        }
    }

    @Test
    public void insertProductTest() {
        ProductDTO productDTO = new ProductDTO("사과", 3000, "사진링크");
        Product product = productService.insertProduct(productDTO);
        Assertions.assertThat(product.getId()).isEqualTo(1L);
        Assertions.assertThat(product.getName()).isEqualTo("사과");
        Assertions.assertThat(product.getPrice()).isEqualTo(3000);
        Assertions.assertThat(product.getImageUrl()).isEqualTo("사진링크");
    }

    @Test
    public void getProductTest() {
        ProductDTO productDTO = new ProductDTO("사과", 3000, "사진링크");
        productService.insertProduct(productDTO);

        Product product = productService.getProduct(1L);
        Assertions.assertThat(product.getId()).isEqualTo(1L);
        Assertions.assertThat(product.getName()).isEqualTo("사과");
        Assertions.assertThat(product.getPrice()).isEqualTo(3000);
        Assertions.assertThat(product.getImageUrl()).isEqualTo("사진링크");
    }

    @Test
    public void getProductAllTest() {
        ProductDTO productDTO = new ProductDTO("사과", 3000, "사진링크");
        productService.insertProduct(productDTO);

        List<Product> productAll = productService.getProductAll();
        Assertions.assertThat(productAll.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(productAll.get(0).getName()).isEqualTo("사과");
        Assertions.assertThat(productAll.get(0).getPrice()).isEqualTo(3000);
        Assertions.assertThat(productAll.get(0).getImageUrl()).isEqualTo("사진링크");
    }

    @Test
    public void updateProduct() {
        ProductDTO productDTO = new ProductDTO("사과", 3000, "사진링크");
        Product product = productService.insertProduct(productDTO);

        ProductDTO productUpdatedDTO = new ProductDTO("사과", 5500, "사진링크2");

        Product productUpdated = productService.updateProduct(product.getId(), productUpdatedDTO);
        Assertions.assertThat(productUpdated.getName()).isEqualTo("사과");
        Assertions.assertThat(productUpdated.getPrice()).isEqualTo(5500);
        Assertions.assertThat(productUpdated.getImageUrl()).isEqualTo("사진링크2");
    }

    @Test
    public void deleteProduct() {
        ProductDTO productDTO = new ProductDTO("사과", 3000, "사진링크");
        productService.insertProduct(productDTO);

        productDTO = new ProductDTO("바나나", 1500, "사진링크2");
        productService.insertProduct(productDTO);

        productService.deleteProduct(1L);

        List<Product> productAll = productService.getProductAll();
        Assertions.assertThat(productAll.size()).isEqualTo(1);
        Assertions.assertThat(productAll.get(0).getId()).isEqualTo(2L);
    }
}
