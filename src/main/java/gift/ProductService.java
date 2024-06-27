package gift;

import gift.domain.product.Product;
import gift.domain.product.ProductRepository;
import gift.dto.ProductRequestDto;

public class ProductService {
    ProductRepository productRepository = ProductRepository.getInstance();

    public void addProduct(ProductRequestDto requestDto){
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImgUrl());
        productRepository.save(product);
    }
}
