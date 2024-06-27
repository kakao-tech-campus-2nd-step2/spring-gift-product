package gift.repository;

import gift.entity.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionDB {
    private Map<Long, Product> products =new HashMap<>(); //여기에 final 붙이는게 맞나요?

    public CollectionDB() {
        products.put(8146027L,new Product("아이스 카페 아메리카노 T",4500,"https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

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
