package gift.Service;

import gift.domain.Product;
import gift.domain.ProductDao;
import gift.domain.ProductDaoImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    public Product getProductById(Long id){
        return productDao.findById(id);
    }

    public void saveProduct(Product product){
        productDao.save(product);
    }

    public void updateProduct(Product product){
        productDao.update(product);
    }

    public void deleteProduct(Long id){
        productDao.deleteById(id);
    }

}
