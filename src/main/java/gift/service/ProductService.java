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

    /*
    public Product findById(int id){

    }

    public List<Product> findAll(){

    }

    public void deleteById(Long id){

    }

    public void updateById(Long id){

    } */
}
