package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."))
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
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        return productRepository.delete(id);
    }
/*
    public void updateById(Long id){

    } */
}
