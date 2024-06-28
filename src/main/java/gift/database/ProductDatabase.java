package gift.database;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProductDatabase {
    // 지인이 동시성 문제를 해결하기 위해 ConcurrentHashMap 이라는 것을 사용해보자고 해서...
    // 좀 더 알아봐야겠다!
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    // 자동으로 제품 ID를 등록하기 위해
    private final AtomicLong idGenerator = new AtomicLong(0);

    public void save(Product product){
        if (product.getId() == null) {
            product.setId(idGenerator.incrementAndGet());
        }
        products.put(product.getId(), product);
    }

    public Product findById(Long id){
        return products.get(id);
    }

    public Product getByName(String name){
        return findByName(name).orElseThrow(NoSuchElementException::new);
    }

    public Optional<Product> findByName(String name){
        return products.values()
            .stream()
            .filter(product -> product.getName().equals(name))
            .findAny();
    }

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public void deleteById(Long id){
        products.remove(id);
    }
}
