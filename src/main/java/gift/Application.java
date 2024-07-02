package gift;

import gift.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private ProductDao productDao;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        productDao.createProductTable();


    }
}
