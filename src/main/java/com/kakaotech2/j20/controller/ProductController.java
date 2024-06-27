package com.kakaotech2.j20.controller;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품 추가,수정,삭제,조회를 위한 api end-point
 * <p>
 * $/api/products
 */
@RestController
public class ProductController {

    private final ProductService pm;

    public ProductController(ProductService pm) {
        this.pm = pm;
    }

    @GetMapping("/api/products")
    public List<ProductDTO> getList(){
        List<ProductDTO> dto = pm.readAll();
        return dto;
    }


    @PostMapping("/api/products")
    public void add(ProductDTO dto){
        pm.create(dto);
    }

    @PutMapping("/api/products")
    public void update(@RequestParam("id") Long id, @RequestBody ProductDTO dto){
        if (dto.getId()==null){
            //TODO : http 메소드로 반환
            throw new IllegalArgumentException("id is null");
        }
        changeCheckAndUpdate(id, dto);
    }

    @DeleteMapping("/api/products")
    public void delete(@RequestParam("id") Long id){
        pm.delete(id);
    }


    private void changeCheckAndUpdate(Long id, ProductDTO dto) {
        if (dto.getName()!=null){
            pm.updateName(id, dto.getName());
        }
        if (dto.getPrice()!=null){
            pm.updatePrice(id, dto.getPrice());
        }
        if (dto.getImageUrl()!=null){
            pm.updateImageUrl(id, dto.getImageUrl());
        }
    }


}
