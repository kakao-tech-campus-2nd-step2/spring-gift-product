package gift.product.service;

import gift.product.dto.ProductDTO;
import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import java.util.List;

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

    public List<Product> getProductAll() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = new Product(id, productDTO.name(), productDTO.price(), productDTO.imageUrl());
        productRepository.update(product);
        return product;
    }
}
