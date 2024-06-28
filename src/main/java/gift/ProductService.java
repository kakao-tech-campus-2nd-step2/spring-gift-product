package gift;

import gift.domain.product.Product;
import gift.domain.product.ProductRepository;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;

public class ProductService {
    ProductRepository productRepository = ProductRepository.getInstance();

    public void addProduct(ProductRequestDto requestDto){
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImgUrl());
        productRepository.save(product);
    }

    public List<ProductResponseDto> findAll(){
        return productRepository.findAll().stream().map(ProductResponseDto::new).toList();
    }

    public ProductResponseDto findProduct(Long id){
        return new ProductResponseDto(productRepository.findById(id));
    }

    public ProductResponseDto editProduct(Long id, ProductRequestDto request){
        Product product = productRepository.findById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImgUrl(request.getImgUrl());
        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long id){
        if (productRepository.deleteById(id) == null) {
            throw new IllegalArgumentException();
        }
    }
}
