package gift.product.service;

import gift.product.model.ProductVo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Map<Long, ProductVo> products = new HashMap<>();

    public void addProduct(ProductVo productVo) {
        products.put(productVo.getId(), productVo);
    }

    public void modifyProduct(ProductVo productVo) {
        products.put(productVo.getId(), productVo);
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }

    public Collection<ProductVo> getAllProducts() {
        return products.values();
    }

    public Collection<ProductVo> searchProducts(String keyword) {
        return products.values().stream()
            .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
}
