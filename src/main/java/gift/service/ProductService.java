package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto save(ProductRequestDto productDto){
        Long id = productRepository.save(productDto.toEntity());
        return new ProductResponseDto(id,productDto.getName(),productDto.getPrice(),productDto.getImageUrl());
    }


    public ProductResponseDto findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("ID가 " + id + "인 상품이 존재하지 않습니다."))
                .toDto();
    }

    public List<ProductResponseDto> findAll(){
        return productRepository.findAll()
                .stream()
                .map(Product::toDto)
                .toList();
    }

    public Long deleteById(Long id){
        productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("ID가 " + id + "인 상품이 존재하지 않습니다."));
        return productRepository.delete(id);
    }

    public ProductResponseDto updateById(Long id, ProductRequestDto productDto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("ID가 " + id + "인 상품이 존재하지 않습니다."));
        ProductResponseDto productResponseDto = productRepository.update(id, productDto.toEntity()).toDto();
        productResponseDto.setId(id);
        return productResponseDto;
    }
}
