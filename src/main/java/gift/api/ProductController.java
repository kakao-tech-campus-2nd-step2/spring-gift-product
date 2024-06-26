package gift.api;

import gift.application.ProductService;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 전체 조회
    @GetMapping
    @ResponseBody
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    @ResponseBody
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 상품 추가
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    // 상품 하나 삭제
    @DeleteMapping("/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    // 상품 전체 삭제
    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    // 상품 수정
}
