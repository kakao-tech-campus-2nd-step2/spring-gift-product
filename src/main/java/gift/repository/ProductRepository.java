package gift.repository;

import gift.controller.dto.ProductDTO;
import gift.domain.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final Map<Long, Product> map = new HashMap<>();
    private Long id =1L;

    public Product findById(Long id){
        return map.get(id);
    }
    public List<Product> findALL(){
        return new ArrayList<>(map.values());
    }
    public String create(ProductDTO productDTO){
        Product product = new Product(id,productDTO.getName(),productDTO.getPrice(),productDTO.getImageUrl());
        map.put(id++,product);
        return "저장 성공";
    }
    public String update(ProductDTO productDTO,Long id){
        Product product = map.get(id);
        product.setName(productDTO.getName());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());

        return "저장 성공";
    }

    public String delete(Long id){
        if (!map.containsKey(id)){
            return null;
        }
        map.remove(id);
        return "삭제 성공";
    }

}