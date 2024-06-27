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
}
