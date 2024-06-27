package gift;

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

    public List<Product> getAllProduct() {
        if (productRepository.isEmptyProductList()) {
            throw new NoSuchElementException("No Product in DB");
        }
        return productRepository.getAllProduct();
    }

    public void addProduct(ProductRequestDto productRequestDto) {
        if (productRepository.isExistProductId(productRequestDto.getId())) {
            throw new IllegalArgumentException("Already Exist Product ID");
        }
        productRepository.addProduct(productRequestDto);
    }

    public void updateProduct(ProductRequestDto productRequestDto) {
        if (!productRepository.isExistProductId(productRequestDto.getId())) {
            throw new NoSuchElementException("Invalid Product ID");
        }
        productRepository.updateProduct(productRequestDto);
    }
}
