package gift.repository;

import gift.dto.ProductDTO;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryRepository {

    private final Map<Long, ProductDTO> products = new HashMap<>();
    private long id = 1;

    public Collection<ProductDTO> getProducts() {
        return products.values();
    }

    public ProductDTO getProduct(Long id) {
        return products.get(id);
    }

    public void addProduct(ProductDTO productDTO) {
        ProductDTO newProductDTO = new ProductDTO(id, productDTO.name(), productDTO.price(), productDTO.imageUrl());
        products.put(id++, newProductDTO);
    }

    public void updateProduct(long id, ProductDTO productDTO) {
        ProductDTO updatedProductDTO = new ProductDTO(id, productDTO.name(), productDTO.price(), productDTO.imageUrl());
        products.replace(id, updatedProductDTO);
    }

    public void deleteProduct(long id) {
        products.remove(id);
    }
}
