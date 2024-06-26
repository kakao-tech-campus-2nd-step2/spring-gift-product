package gift.Mapper;

import gift.DTO.ProductDTO;
import gift.Domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductDTO createProduct(Long index, Product.CreateProduct create) {
    return new ProductDTO(index, create.getName(), create.getPrice(), create.getImageUrl());
  }

  public ProductDTO updateProduct(ProductDTO product, Product.UpdateProduct update) {
    product.setName(update.getName());
    product.setPrice(update.getPrice());
    product.setImageUrl(update.getImageUrl());
    return product;
  }
}
