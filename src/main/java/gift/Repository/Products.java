package gift.Repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
