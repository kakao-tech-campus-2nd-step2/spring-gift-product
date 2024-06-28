package gift.service;

import gift.dto.ProductDTO;
import gift.repository.H2Repository;
import gift.repository.MemoryRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

//    private final MemoryRepository repository;
//
//    @Autowired
//    public ProductService(MemoryRepository memoryRepository) {
//        this.repository = memoryRepository;
//    }

    private final H2Repository repository;

    @Autowired
    public ProductService(H2Repository h2Repository) {
        this.repository = h2Repository;
    }

    public Collection<ProductDTO> getProducts() {
        return repository.getProducts();
    }

    public ProductDTO getProduct(Long id) {
        return repository.getProduct(id);
    }

    public boolean addProduct(ProductDTO productDTO) {
        if (repository.getProducts().contains(productDTO)) {
            return false;
        }
        repository.addProduct(productDTO);
        return true;
    }

    public boolean updateProduct(long id, ProductDTO productDTO) {
        if (repository.getProduct(id) == null) {
            return false;
        }
        repository.updateProduct(id, productDTO);
        return true;
    }

    public boolean deleteProduct(long id) {
        if (repository.getProduct(id) == null) {
            return false;
        }
        repository.deleteProduct(id);
        return true;
    }
}
