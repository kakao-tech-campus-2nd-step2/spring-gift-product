package gift;

import gift.model.ProductDaoImpl;
import gift.model.ProductRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    @Autowired
    private ProductDaoImpl productDaoImpl;

    @PostConstruct
    public void init() {
        productDaoImpl.save(new ProductRequest("product1", 10000, "image1.png"));
        productDaoImpl.save(new ProductRequest("product2", 20000, "image2.png"));
        productDaoImpl.save(new ProductRequest("product3", 30000, "image3.png"));
    }
}
