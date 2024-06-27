package gift.config;

import gift.model.repository.ProductRepository;
import gift.model.Products;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ProductRepository products(){
        return new Products();
    }

}
