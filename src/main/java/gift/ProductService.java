package gift;

import gift.domain.product.Product;
import gift.domain.product.ProductInMemoryRepository;
import gift.domain.product.ProductJdbcRepository;
import gift.domain.product.ProductRepository;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductJdbcRepository repository;

    @Autowired
    public ProductService(ProductJdbcRepository repository) {
        this.repository = repository;
    }

    public void addProduct(ProductRequestDto requestDto){
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImgUrl());
        repository.save(product);
    }

    public List<ProductResponseDto> findAll(){
        return repository.findAll().stream().map(ProductResponseDto::new).toList();
    }

    public ProductResponseDto findProduct(Long id){
        return new ProductResponseDto(repository.findById(id));
    }

    public ProductResponseDto editProduct(Long id, ProductRequestDto request){
        int result = repository.update(id,request);
        if (result == 0){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        return new ProductResponseDto(repository.findById(id));
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
        //TODO 예외처리
    }
}
