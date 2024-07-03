package gift.controller;

import gift.global.response.ResponseMaker;
import gift.global.response.ResultCode;
import gift.global.response.ResultResponseDto;
import gift.global.response.SimpleResultResponseDto;
import gift.service.ProductService;
import gift.dto.ProductDTO;
import gift.model.Product;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 추가
     *
     * @param productDTO
     * @return 결과 메시지
     */
    @PostMapping
        public ResponseEntity<SimpleResultResponseDto> postProduct(@Valid @ModelAttribute ProductDTO productDTO) {
        productService.postProduct(productDTO);
        return ResponseMaker.createSimpleResponse(ResultCode.CREATE_PRODUCT_SUCCESS);
    }

    /**
     * 전체 상품 목록 조회
     *
     * @return products (상품 목록)
     */
    @GetMapping
    public ResponseEntity<ResultResponseDto<List<Product>>> getProducts() {
        List<Product> products = productService.getProducts();
        // 성공 시
        return ResponseMaker.createResponse(ResultCode.GET_ALL_PRODUCTS_SUCCESS, products);
    }


    /**
     * 상품 수정
     *
     * @param id
     * @param productDTO
     * @return 결과 메시지
     */
    @PutMapping("/{id}")
    public ResponseEntity<SimpleResultResponseDto> updateProduct(@PathVariable("id") Long id,
        @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseMaker.createSimpleResponse(ResultCode.UPDATE_PRODUCT_SUCCESS);
    }


    /**
     * 해당 ID 리스트에 속한 상품 삭제
     *
     * @param productIds
     * @return 결과 메시지
     */
    @DeleteMapping
    public ResponseEntity<?> deleteSelectedProducts(@RequestBody List<Long> productIds) {
        productService.deleteProductsByIds(productIds);
        return ResponseMaker.createSimpleResponse(ResultCode.DELETE_PRODUCTS_SUCCESS);
    }

    /**
     * 해당 ID 를 가진 상품 삭제
     *
     * @param id
     * @return 결과 메시지
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseMaker.createSimpleResponse(ResultCode.DELETE_PRODUCT_SUCCESS);
    }


}
