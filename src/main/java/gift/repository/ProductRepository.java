package gift.repository;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.List;


public interface ProductRepository {

    Product save(ProductForm form);

    boolean delete(Long id);

     Product edit(Long id, ProductForm form);

    Product findById(Long id);

    List<Product> findAll();
}