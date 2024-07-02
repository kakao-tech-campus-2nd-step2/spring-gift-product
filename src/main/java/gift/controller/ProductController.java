package gift.controller;

import org.springframework.web.bind.annotation.*;
import gift.model.Product;
import gift.model.ProductDao;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * 새 상품을 생성하고 맵에 저장함
     *
     * @param product 저장할 상품 객체
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDao.createProduct(product);
    }

    /**
     * 주어진 ID에 해당하는 상품을 반환함
     *
     * @param id 조회할 상품의 ID
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productDao.getProduct(id);
    }

    /**
     * 모든 상품을 반환함
     */
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productDao.getAllProducts();
    }

    /**
     * 주어진 ID에 해당하는 상품을 삭제함
     *
     * @param id 삭제할 상품의 ID
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
    }

    /**
     * 주어진 ID에 해당하는 상품을 갱신함
     *
     * @param id      갱신할 상품의 ID
     * @param product 갱신할 상품 객체
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productDao.updateProduct(id, product);
    }
}