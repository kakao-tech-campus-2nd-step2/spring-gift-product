package service;

import domain.Product;
import dto.ProductRequestDto;
import dto.ProductResponseDto;
import exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto save(ProductRequestDto product){
        return productRepository.save(product.toEntity()).toDto();
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

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return product.toDto();
    }
}
