package gift.repository;

import gift.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class CollectionDB {
    private Map<Long, Product> products =new HashMap<>(); //여기에 final 붙이는게 맞나요?


    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long,Product> products){
        this.products = products;
    }
    public void addProducts(Long id, Product product){
        products.put(id,product);
    }
}
