package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //전체 상품 조회 기능
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //단일 상품 조회 기능
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    //상품 추가 기능
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    //상품 수정 기능
    public void updateProduct(Long id, Product product) {
        productRepository.update(id, product);
    }

    //상품 삭제 기능
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
