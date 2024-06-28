package gift.product.controller;

import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import gift.global.utils.ResponseHelper;
import gift.product.dto.ProductRequestDto;
import gift.product.domain.Product;
import gift.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<ResultResponseDto<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseHelper.createResponse(ResultCode.GET_ALL_PRODUCTS_SUCCESS, products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponseDto<Product>> getProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        return ResponseHelper.createResponse(ResultCode.GET_PRODUCT_BY_ID_SUCCESS, product);
    }

    @PostMapping("")
    public ResponseEntity<SimpleResultResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto.toServiceDto());
        return ResponseHelper.createSimpleResponse(ResultCode.CREATE_PRODUCT_SUCCESS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductRequestDto productRequestDto) {
        productService.updateProduct(productRequestDto.toServiceDto(id));
        return ResponseHelper.createSimpleResponse(ResultCode.UPDATE_PRODUCT_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseHelper.createSimpleResponse(ResultCode.DELETE_PRODUCT_SUCCESS);
    }
}