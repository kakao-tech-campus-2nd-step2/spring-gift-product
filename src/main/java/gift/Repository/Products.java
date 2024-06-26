package gift.Repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class Products {
    private final Map<Long, Product> products;
    public Products(){
        this.products=new HashMap<>();
    }

    //상품추가
    public boolean addProduct(Product product){
        if(products.containsKey(product.getId())){
            return false;
        }
        products.put(product.getId(),product);
        return true;
    }
    public boolean deleteProduct(Long id){
        if(products.containsKey(id)){
            products.remove(id);
            return true;
        }

        return false;
    }
    public boolean updateProduct(Product product){
        if(products.containsKey(product.getId())){
            products.remove(product.getId());
            products.put(product.getId(),product);
            return true;
        }
        return false;
    }
    //모든상품 조회
    public List<Product> findAll(){
        return products.values()
                .stream()
                .toList();
    }
    //단건 조회
    public Product getProduct(Long id){
        return findAll().stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


}
