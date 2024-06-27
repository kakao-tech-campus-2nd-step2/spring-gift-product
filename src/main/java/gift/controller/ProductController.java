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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;

    }

    @GetMapping("/api/products")
    public List<ProductResponse> getAllProducts(){

        return productRepository.findAll().stream()
                .filter(product -> !product.isDeleted())
                .map(ProductResponse::fromModel)
                .toList();
    }

    @GetMapping("/api/products/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);
        if(findedProduct.isDeleted()){
            throw new IllegalArgumentException();
        }

        return ProductResponse.fromModel(findedProduct);
    }

    @PostMapping("/api/products")
    public void productSave(@RequestBody ProductRequest newProduct){
        productRepository.save(newProduct.toModel());
    }

    @PatchMapping("/api/products/{id}")
    public void productModify(@PathVariable("id") Long id, @RequestBody ProductRequest modifyProduct){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);
        if(findedProduct.isDeleted()){
            throw new IllegalArgumentException();
        }
        productRepository.save(modifyProduct.toModel(id));
    }

    @DeleteMapping("/api/products/{id}")
    public void productDelete(@PathVariable("id") Long id){
        Product findedProduct = productRepository.find(id).orElseThrow(IllegalArgumentException::new);
        if(findedProduct.isDeleted()){
            throw new IllegalArgumentException();
        }

        findedProduct.delete();
        productRepository.save(findedProduct);
    }
}
