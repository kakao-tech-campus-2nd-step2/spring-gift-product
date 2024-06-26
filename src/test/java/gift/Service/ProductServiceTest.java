package gift.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gift.Domain.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

  @Autowired
  private ProductService productService;
  private List<Product.CreateProduct> list = new ArrayList<>();

  @BeforeEach
  public void BeforeAll() {
    list.add(new Product.CreateProduct("test1", 100, "test1"));
    list.add(new Product.CreateProduct("test2", 100, "test2"));
    list.add(new Product.CreateProduct("test3", 100, "test3"));

    for (Product.CreateProduct p : list) {
      productService.createProduct(p);
    }
  }

  @Test
  @DisplayName("상품 생성")
  public void CreateProduct() {
    productService.getProduct(3l);
    //  how
    Product.CreateProduct temp = new Product.CreateProduct("test4", 100, "test4");
    //  when
    productService.createProduct(temp);
    //  then
    assertEquals(temp.getName(),
        productService.getProduct((long) productService.getProductList().size() - 1).getName());
  }

  @Test
  @DisplayName("상품 수정")
  public void UpdateProduct() {
    productService.getProduct(3l);
    //  how
    Product.UpdateProduct temp = new Product.UpdateProduct("test4-1", 100, "test4-1");
    //  when
    productService.updateProduct(temp, (long) productService.getProductList().size() - 1);
    //  then
    assertEquals(temp.getName(),
        productService.getProduct((long) productService.getProductList().size() - 1).getName());
  }

  @Test
  @DisplayName("상품 삭제")
  public void removeProduct() {
    productService.getProduct(3l);
    //  how
    int before = productService.getProductList().size();
    Long target = (long) before - 1;
    //  when
    productService.deleteProduct(target);
    //  then
    assertEquals(before - 1, productService.getProductList().size());
  }

}