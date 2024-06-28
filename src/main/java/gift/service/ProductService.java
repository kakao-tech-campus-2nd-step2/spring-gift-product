package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts(); // 모든 상품을 조회
    }

    // Read(단일 상품) - getProduct()
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    // Create(생성) - addProduct()
    public String createProduct(Product product) {
        productRepository.addProduct(product);
        return "상품 생성";
    }

    // Update(수정) - updateProduct()
    public String editProduct(Long id, Product product) {
        Product existingProduct = productRepository.getProduct(id);
        if (existingProduct != null) {
            productRepository.updateProduct(id, product);
            return "상품 수정";
        }
        return "존재하지 않는 상품입니다.";
    }

    public String deleteProduct(Long id) {
        try {
            String response = productRepository.removeProduct(id);
            if (response.equals("상품 삭제")) {
                return "상품 삭제"; // 성공적으로 삭제되었음을 나타내는 메시지
            }
            return "삭제할 상품이 존재하지 않습니다.";
        } catch (Exception e) {
            return "삭제 중 오류가 발생했습니다."; // 에러 발생 시 메시지
        }
    }

}
