package gift.service;

import gift.domain.repository.ProductRepository;
import gift.domain.model.Product;
import gift.domain.model.ProductDto;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        if (!productRepository.isExistProductId(id)) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        return productRepository.getProductById(id);
    }

    public List<ProductDto> getAllProduct() {
        if (productRepository.isEmptyProductList()) {
            throw new NoSuchElementException("No Product in DB");
        }
        return productRepository.getAllProduct();
    }

    public void addProduct(ProductDto productDto) {
        if (productRepository.isExistProductId(productDto.getId())) {
            throw new IllegalArgumentException("Already Exist Product ID");
        }
        productRepository.addProduct(productDto);
    }

    public void updateProduct(ProductDto productDto) {
        if (!productRepository.isExistProductId(productDto.getId())) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        productRepository.updateProduct(productDto);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.isExistProductId(id)) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        productRepository.deleteProduct(id);
    }
}
