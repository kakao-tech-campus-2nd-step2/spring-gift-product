package gift.service;

import gift.domain.Product;
import gift.dto.ProductDto;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long addProduct(ProductDto productDto){
        Product product = Product.toEntity(productDto);

        productRepository.save(product);

        return product.getId();
    }

    public ProductDto findProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품은 존재하지 않습니다."));
        return ProductDto.toDto(product);
    }

    public List<ProductDto> findAllProducts(){
        return productRepository.findAll().stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, int price){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품은 존재하지 않습니다."));

        product.setPrice(price);

        return ProductDto.toDto(product);
    }

    public Long deleteProduct(Long id){
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품은 존재하지 않습니다."));
        return productRepository.delete(findProduct.getId());
    }
}
