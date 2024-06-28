package gift.controller;


import gift.controller.req.ProductRequest;
import gift.controller.res.ProductResponse;
import gift.model.Product;
import gift.model.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;

    }

    @GetMapping("")
    public List<ProductResponse> productList(){

        return productRepository.findAll().stream()
                .map(ProductResponse::fromModel)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse productDetails(@PathVariable("id") Long id){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);

        return ProductResponse.fromModel(findedProduct);
    }

    @PostMapping("")
    public void productSave(@RequestBody ProductRequest newProduct){
        productRepository.save(newProduct.toModel());
    }

    @PatchMapping("/{id}")
    public void productModify(@PathVariable("id") Long id, @RequestBody ProductRequest modifyProduct){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);

        productRepository.save(modifyProduct.toModel(id));
    }

    @DeleteMapping("/{id}")
    public void productDelete(@PathVariable("id") Long id){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);

        findedProduct.delete();
        productRepository.save(findedProduct);
    }
}
