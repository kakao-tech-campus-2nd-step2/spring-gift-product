package gift.Controller;

import gift.DTO.ProductDTO;
import gift.Domain.Product;
import gift.Domain.Product.ProductSimple;
import gift.Service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductSimple> getProductList() {
    return productService.getProductList();
  }

  @GetMapping("/{id}")
  public ProductDTO getProduct(@PathVariable long id) {
    return productService.getProduct(id);
  }

  @PostMapping
  public void createProduct() {
    productService.createProduct(create);
  }

  @PutMapping("/{id}")
  public void updateProduct(@PathVariable long id) {
    productService.updateProduct(update, id);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
  }
}
