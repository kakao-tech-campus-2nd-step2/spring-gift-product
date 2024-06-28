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
        // JDBC를 이용한 데이터베이스 접근 방식 선택
        return new JdbcProductRepository(dataSource);
    }

    @PostConstruct
    private void initializeDB() {
        // 데이터베이스 초기화를 위한 SQL 스크립트 실행
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("sql/ddl.sql")); // 데이터베이스 초기화 스크립트 경로 지정
        DatabasePopulatorUtils.execute(populator, dataSource); // 데이터베이스 초기화 실행
    }
}
