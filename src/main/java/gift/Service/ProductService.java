package gift.Service;

import gift.domain.Product;
import gift.domain.ProductDaoImpl;
import gift.domain.ProductValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDaoImpl productDao;

    private final ProductValidator productValidator = new ProductValidator();

    public ProductService(ProductDaoImpl productDao){
        this.productDao = productDao;
        productDao.createProductTable();
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

    public void updateProduct(Product product,Long id){
        productDao.update(product,id);
    }

    public void deleteProduct(Long id){
        productDao.deleteById(id);
    }

}
