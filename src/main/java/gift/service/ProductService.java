package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Read(전체 상품) - ProductGET()
    public Collection<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    // Read(단일 상품)
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    // Create(생성) - productPost()
    public String createProduct(Product product) {
        if (!productRepository.containsProduct(product.getId())) {
            productRepository.addProduct(product);
            return "상품 생성";
        }
        return "상품이 이미 존재합니다";
    }

    // Update(수정) - productPut()
    public String updateProduct(Long id, Product product) {
        if (productRepository.containsProduct(id)) {
            productRepository.updateProduct(id, product);
            return "상품 수정";
        }
        return "존재하지 않는 상품입니다.";
    }

    // Delete(삭제) - productDelete
    public String deleteProduct(Long id) {
        if (productRepository.containsProduct(id)) {
            productRepository.removeProduct(id);
            return "상품 삭제";
        }
        return "삭제할 상품이 존재하지 않습니다.";
    }
}
