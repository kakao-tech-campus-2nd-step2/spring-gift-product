package gift.product.service;

import gift.product.dao.ProductDao;
import gift.product.model.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private static ProductDao productDao;

    public void addProduct(ProductVo productVo) {
        productDao.addProduct();
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
