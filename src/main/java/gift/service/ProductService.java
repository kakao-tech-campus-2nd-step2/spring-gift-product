package gift.service;

import gift.controller.ProductDto;
import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product register(ProductDto productDto){
        validateDuplicateProduct(productDto);
        Product product = Product.dtoToEntity(productDto);
        return productRepository.save(product);
    }
    private void validateDuplicateProduct(ProductDto productDto){
        productRepository.findByName(productDto.getName())
                .ifPresent(p -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                });
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long productId){
        return productRepository.findById(productId);
    }
}
