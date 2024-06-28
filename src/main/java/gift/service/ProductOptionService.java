package gift.service;

import gift.dto.ProductDto;
import gift.dto.ProductOptionDto;
import gift.model.Product;
import gift.model.ProductOption;
import gift.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionService {

    private final ProductOptionRepository repository;

    public ProductOptionService(ProductOptionRepository repository) {
        this.repository = repository;
    }

    public ProductOption addOption(ProductOptionDto productOptionDto) {
        var product = new ProductOption(productOptionDto.productId(),
                productOptionDto.name(),
                productOptionDto.additionalPrice());
        return repository.save(product);
    }

    public ProductOption updateProduct(Long id, ProductOptionDto productOptionDto) {
        var productOption = repository.findById(id);
        productOption.setName(productOptionDto.name());
        productOption.setAdditionalPrice(productOptionDto.additionalPrice());
        repository.update(productOption);
        return productOption;
    }

    public ProductOption getOption(Long id) {
        return repository.findById(id);
    }

    public List<ProductOption> getOptions(Long productId) {
        return repository.findAll(productId);
    }
}
