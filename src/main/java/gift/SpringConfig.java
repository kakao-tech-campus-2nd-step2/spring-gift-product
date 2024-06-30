package gift;

import gift.controller.AdminController;
import gift.repository.JdbcProductRepository;
import gift.repository.MemoryProductRepository;
import gift.repository.ProductRepository;
import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

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

    @PostConstruct
    private void initializeDB() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("sql/ddl.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
