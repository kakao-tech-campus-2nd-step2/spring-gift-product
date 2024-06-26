package gift.config;

import gift.model.Product;
import gift.model.ProductRepository;
import gift.model.Products;
import gift.model.RepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ProductRepository products(){
        return new Products();
    }

}
