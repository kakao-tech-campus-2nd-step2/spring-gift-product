package gift.controller;

import gift.model.Product;
import gift.model.Products;
import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
@RequestMapping("/products")
public class ProductController {

    Products products = new Products();

    // 상품을 추가하는 메서드.
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        // 상품을 추가하는 메서드.
        Product createdProduct = products.put(product);
        return createdProduct;
    }

    // 전체 상품을 조회하는 메서드
    @GetMapping("/read")
    public Collection<Product> readProducts() {
        return products.getProducts();
    }

    // 하나의 상품을 조회하는 메서드
    @GetMapping("/{id}/read")
    public Product readProduct(@PathVariable int id) {
        return products.getProduct(id);
    }

    // id가 i인 상품을 수정하는 메서드
    @PatchMapping("/{id}/update")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return products.updateProduct(id, product);
    }

    // id가 i인 상품을 삭제하는 메서드
    @DeleteMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        products.deleteProduct(id);
        return "삭제가 완료되었습니다.";
    }

    // 모든 상품을 삭제하는 메서드
    @DeleteMapping("/delete")
    public String deleteAllProducts() {
        products.deleteAllProducts();
        return "모든 데이터가 삭제되었습니다.";
    }
}
