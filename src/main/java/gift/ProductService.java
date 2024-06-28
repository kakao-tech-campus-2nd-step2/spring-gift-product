package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product makeProduct(ProductRequestDto requestDto) {
        Product getProduct = productDao.select(requestDto.getId());

        if (getProduct == null) {
            Product product = new Product(
                    requestDto.getId(),
                    requestDto.getName(),
                    requestDto.getPrice(),
                    requestDto.getImageUrl()
            );
            productDao.insert(product);
            return product;
        }
        return null;
    }
    
    public List<Product> getAllProducts() {
        return productDao.selectAll();
    }

    public Product getProduct(Long id) {
        return productDao.select(id);
    }
}
