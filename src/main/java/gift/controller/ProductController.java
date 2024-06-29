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

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> productList() {
        List<Product> foundProduct = productRepository.findAll();

        return ResponseEntity.ok()
                .body(foundProduct.stream().map(ProductResponse::fromModel).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> productDetails(@PathVariable("id") Long id) {
        Product foundProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        return ResponseEntity.ok()
                .body(ProductResponse.fromModel(foundProduct));
    }

    @PostMapping("")
    public ResponseEntity<String> productSave(@RequestBody ProductRequest newProduct) {
        productRepository.save(newProduct.toModel());

        return ResponseEntity.created(null)
                .body("Product created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> productModify(@PathVariable("id") Long id,
                                                @RequestBody ProductRequest modifyProduct) {
        Product findedProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        productRepository.save(modifyProduct.toModel(id));

        return ResponseEntity.ok()
                .body("Product modified");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> productDelete(@PathVariable("id") Long id) {
        Product findedProduct = productRepository.find(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));

        findedProduct.delete();
        productRepository.save(findedProduct);

        return ResponseEntity.ok()
                .body("Product deleted");
    }
}
