package gift;
import gift.controller.AdminController;
import gift.repository.JdbcProductRepository;
import gift.repository.MemoryProductRepository;
import gift.repository.ProductRepository;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SpringConfig {


    @Configuration
    public class SpringConfig {

        private final DataSource dataSource;

        @Autowired
        public SpringConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        public AdminController adminController() {
            return new AdminController(productRepository());
        }

        @Bean
        public ProductRepository productRepository() {
            //        return new MemoryProductRepository();
            return new JdbcProductRepository(dataSource);
        }
    }