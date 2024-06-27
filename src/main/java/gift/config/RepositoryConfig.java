package gift.config;

import gift.model.dao.JDBCProductDao;
import gift.model.dao.JDBCTemplateProductDao;
import gift.model.repository.ProductRepository;
import gift.model.dao.MapProductDao;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class RepositoryConfig {

    @Bean
    public ProductRepository products(JdbcTemplate jdbcTemplate) {
        return new JDBCTemplateProductDao(jdbcTemplate);
    }
}
