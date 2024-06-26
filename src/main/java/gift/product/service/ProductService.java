package gift.product.service;

import gift.product.dto.ProductDTO;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public Product insertProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO.name(), productDTO.price(), productDTO.imageUrl());
        productRepository.save(product);

        return product;
    }


}
