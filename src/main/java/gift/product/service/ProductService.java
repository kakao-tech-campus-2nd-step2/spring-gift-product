package gift.product.service;

import gift.product.dao.ProductDao;
import gift.product.model.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
        productDao.createProductTable();
    }

    public void addProduct(ProductVo productVo) {
        productDao.addProduct(productVo);
    }

    public void modifyProduct(ProductVo productVo) {
        productDao.modifyProduct(productVo);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    public Collection<ProductVo> getAllProducts() {
        return productDao.listupProducts();
    }

    public Collection<ProductVo> searchProducts(String keyword) {
        return productDao.searchProducts(keyword);
    }
}
