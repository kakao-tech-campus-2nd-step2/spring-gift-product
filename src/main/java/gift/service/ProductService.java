package gift.service;

import gift.controller.dto.ProductCreateRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.controller.dto.ProductUpdateRequestDto;
import gift.domain.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        return productRepository
            .findAll()
            .stream()
            .map(ProductResponseDto::from)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Product not found. productId: " + id));
        return ProductResponseDto.from(product);
    }

    @Transactional
    public void addProduct(ProductCreateRequestDto productCreateRequest) {
        Product product = productOf(productCreateRequest);
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long id, ProductUpdateRequestDto productUpdateRequest) {
        Product originalProduct = productRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Product not found. productId: " + id));

        Product updatedProduct = applyUpdate(originalProduct, productUpdateRequest);
        productRepository.save(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.remove(id);
    }

    private Product productOf(ProductCreateRequestDto productCreateRequest) {
        return new Product(
            0L,
            productCreateRequest.name(),
            productCreateRequest.price(),
            productCreateRequest.imageUrl()
        );
    }

    private Product applyUpdate(Product originalProduct,
        ProductUpdateRequestDto productUpdateRequest) {
        String name = originalProduct.getName();
        if (productUpdateRequest.name() != null) {
            name = productUpdateRequest.name();
        }

        Integer price = originalProduct.getPrice();
        if (productUpdateRequest.price() != null) {
            price = productUpdateRequest.price();
        }

        String imageUrl = originalProduct.getImageUrl();
        if (productUpdateRequest.imageUrl() != null) {
            imageUrl = productUpdateRequest.imageUrl();
        }
        return new Product(originalProduct.getId(), name, price, imageUrl);
    }
}
