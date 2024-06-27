package gift.dao;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
    private final Map<Long, Product> products;

    public ProductDao(){
        this.products = new HashMap<>();
    }

    public List<Product> selectAllProduct(){
        return new ArrayList<>(products.values());
    }

    public Product selectProductById(Long id){
        if(!products.containsKey(id)){
            throw new IllegalArgumentException("id와 일치하는 Product가 존재하지 않습니다.");
        }
        return products.get(id);
    }

    public void InsertProduct(Product product){
        if(products.containsKey(product.id())){
            throw new IllegalArgumentException("id와 일치하는 Product가 이미 존재합니다.");
        }
        products.put(product.id(), product);
    }

    public void updateProductById(Long id, Product product){
        if(!products.containsKey(product.id())){
            throw new IllegalArgumentException("id와 일치하는 Product가 존재하지 않습니다.");
        }
        products.put(product.id(), product);
    }

    public void deleteProductById(Long id){
        if(!products.containsKey(id)){
            throw new IllegalArgumentException("id와 일치하는 Product가 존재하지 않습니다.");
        }
        products.remove(id);
    }
}
