package com.kakaotech2.j20.service;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final Map<Long,Product> db;
    private int count=0;

    public ProductServiceImpl() {
        this.db = new HashMap<>();
    }

    /**
     * 상품데이터 전체를 얻습니다.
     * @return 데이터가 없을 경우 null반환
     */
    @Override
    public List<ProductDTO> readAll() {

        List<ProductDTO> requestValue = new ArrayList<>();

        for (Product product : db.values()) {
            ProductDTO dto = new ProductDTO(product);
            requestValue.add(dto);
        }
        return requestValue;
    }

    @Override
    public void create(ProductDTO dto) {
        db.put((long) count,new Product(count,dto.getName(),dto.getPrice(),dto.getImageUrl()));
    }

    @Override
    public void updateName(long id, String name) {
        if(checkId(id)){
             db.get(id).setName(name);
        }
    }

    @Override
    public void updatePrice(long id, int price) {
        if(checkId(id)){
            db.get(id).setPrice(price);
        }

    }

    @Override
    public void updateImageUrl(long id, String url) {
        if(checkId(id)){
            db.get(id).setImageUrl(url);
        }
    }

    @Override
    public void delete(long id) {
        if(checkId(id)){
            db.remove(id);
        }
    }

    private boolean checkId(long id) {
        if(!db.containsKey(id)){
            return false;
        }
        return true;
    }
}
