package gift.controller;

import gift.dto.ProductDto;
import gift.dto.ProductOptionDto;
import gift.model.Product;
import gift.model.ProductOption;
import gift.service.ProductOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/options")
public class ProductOptionController {

    private final ProductOptionService service;

    public ProductOptionController(ProductOptionService service) {
        this.service = service;
    }

    @PutMapping("/add")
    public ResponseEntity<ProductOption> addOption(@RequestBody ProductOptionDto productOptionDto) {
        var productOption = service.addOption(productOptionDto);
        return ResponseEntity.ok(productOption);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductOption> updateOption(@PathVariable Long id, @RequestBody ProductOptionDto productOptionDto) {
        var productOption = service.updateProduct(id, productOptionDto);
        return ResponseEntity.ok(productOption);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOption> getOption(@PathVariable Long id) {
        var productOption = service.getOption(id);
        return ResponseEntity.ok(productOption);
    }
}
