package gift.repository;

import gift.controller.ProductDto;
import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

public class ProductMemoryRepository implements ProductRepository {
    private static final Map<Long, Product> products = new HashMap<>();
    private static long sequence = 0L;
    //final 추가해서 Map에 다른 객체 할당되는거 방지
    //static 추가해서 여러 레포지토리가 있을때도 같은 변수 공유함

    @Override
    public Product save(Product product){
        product.setId(++sequence);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Optional<Product> findByName(String name){
        return products.values().stream().filter(product -> product.getName().equals(name)).findAny();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> updateById(Long id, ProductDto productDto){
        Product product = products.get(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return Optional.of(product);
    }

    @Override
    public Optional<Product> deleteById(Long id){
        Optional<Product> product = findById(id);
        products.remove(id);
        return product;
    }




}
