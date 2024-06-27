package gift.service;

import gift.dto.ProductDTO;
import gift.repository.MemoryRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final MemoryRepository memoryRepository;

    @Autowired
    public ProductService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public Collection<ProductDTO> getProducts() {
        return memoryRepository.getProducts();
    }

    public ProductDTO getProduct(Long id) {
        return memoryRepository.getProduct(id);
    }

    public boolean addProduct(ProductDTO productDTO) {
        if (memoryRepository.getProducts().contains(productDTO)) {
            return false;
        }
        memoryRepository.addProduct(productDTO);
        return true;
    }

    public boolean updateProduct(long id, ProductDTO productDTO) {
        if (memoryRepository.getProduct(id) == null) {
            return false;
        }
        memoryRepository.updateProduct(id, productDTO);
        return true;
    }

    public boolean deleteProduct(long id) {
        if (memoryRepository.getProduct(id) == null) {
            return false;
        }
        memoryRepository.deleteProduct(id);
        return true;
    }
}
