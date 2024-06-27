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

    public ProductDTO getProduct(Long sequence) {
        return memoryRepository.getProduct(sequence);
    }

    public boolean addProduct(ProductDTO productDTO) {
        if (memoryRepository.getProducts().contains(productDTO)) {
            return false;
        }
        memoryRepository.addProduct(productDTO);
        return true;
    }

    public boolean updateProduct(long sequence, ProductDTO productDTO) {
        if (memoryRepository.getProduct(sequence) == null) {
            return false;
        }
        memoryRepository.updateProduct(sequence, productDTO);
        return true;
    }

    public boolean deleteProduct(long sequence) {
        if (memoryRepository.getProduct(sequence) == null) {
            return false;
        }
        memoryRepository.deleteProduct(sequence);
        return true;
    }
}
