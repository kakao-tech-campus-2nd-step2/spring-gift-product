package gift.controller;


import gift.common.exception.ProductNotFoundException;
import gift.controller.req.ProductRequest;
import gift.controller.res.ProductResponse;
import gift.model.Product;
import gift.model.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> productList() {
        List<Product> foundProducts = productRepository.findAll();

        List<ProductResponse> responses = foundProducts.stream()
                .map(ProductResponse::fromModel)
                .toList();

        return ResponseEntity.ok()
                .body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> productDetails(@PathVariable("id") Long id) {
        Product foundProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        ProductResponse response = ProductResponse.fromModel(foundProduct);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping()
    public ResponseEntity<String> productSave(@RequestBody ProductRequest newProduct) {
        productRepository.save(newProduct.toModel());

        return ResponseEntity.created(null) // ORM의 Dirty Checking을 사용할 수 없어 쿼리를 한번 더 날려야해서 null로 설정
                .body("Product created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> productModify(@PathVariable("id") Long id,
                                                @RequestBody ProductRequest modifyProduct) {
        Product foundProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        productRepository.save(modifyProduct.toModel(id));

        return ResponseEntity.ok()
                .body("Product modified");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> productDelete(@PathVariable("id") Long id) {
        Product foundProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        foundProduct.delete();
        productRepository.save(foundProduct);

        return ResponseEntity.ok()
                .body("Product deleted");
    }
}
