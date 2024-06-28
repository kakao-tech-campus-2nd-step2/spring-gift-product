package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        //TODO: 커스텀 예외 추가하기
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        return productRepository.save(new Product(id, product.name(), product.price(), product.imageUrl()));
    }

    public void deleteProduct(Long id) {
        //TODO: 커스텀 예외 추가하기
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.deleteById(id);
    }
}
