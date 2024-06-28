package gift.service;

import gift.dto.ProductRequestDto;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(long id, ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        productRepository.update(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
