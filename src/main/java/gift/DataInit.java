package gift;

import gift.model.Product;
import gift.model.ProductDao;
import gift.model.ProductRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    @Autowired
    private ProductDao productDao;

    @PostConstruct
    public void init() {
        productDao.save(new ProductRequest("product1", 10000, "image1.png"));
        productDao.save(new ProductRequest("product2", 20000, "image2.png"));
        productDao.save(new ProductRequest("product3", 30000, "image3.png"));
    }
}
