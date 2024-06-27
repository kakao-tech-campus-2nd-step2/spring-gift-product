package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findAll(){
    return productRepository.findAll();
  }

  public Optional<Product> findById(Long id){
    return productRepository.findById(id);
  }

  public Product save(Product product){
    return productRepository.save(product);
  }

  public void deleteById(Long id){
    productRepository.deleteById(id);
  }

}

