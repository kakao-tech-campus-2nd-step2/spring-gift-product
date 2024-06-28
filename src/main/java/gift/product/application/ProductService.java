package gift.product.application;

import gift.product.domain.Product;
import gift.product.domain.ProductMemoryRepository;
import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;
import gift.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream().map(ProductResponse::from).toList();
    }

    public ProductResponse findById(Long productId) {
        return productRepository.findById(productId)
                .map(ProductResponse::from)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public void add(ProductCreateCommand product) {
        productRepository.addProduct(product);
    }

    public void update(ProductUpdateCommand command) {
        productRepository.findById(command.productId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        productRepository.updateProduct(command);
    }

    public void delete(Long productId) {
        productRepository.deleteProduct(productId);
    }
}
