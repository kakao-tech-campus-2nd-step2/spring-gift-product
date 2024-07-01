package com.kakaotech2.j20.service;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.Product;
import com.kakaotech2.j20.database.JdbcProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final JdbcProductRepository jdbcProductRepository;

    public ProductServiceImpl(JdbcProductRepository jdbcProductRepository) {
        this.jdbcProductRepository = jdbcProductRepository;
    }

    @Override
    public List<ProductDTO> readAll() {
        var products = jdbcProductRepository.findAllProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(var product : products) {
            productDTOList.add(new ProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public void create(ProductDTO prod) {
        jdbcProductRepository.insertProduct(new Product(null,prod.getName(),prod.getPrice(),prod.getImageUrl()));
    }

    @Override
    public void updateName(long id, String name) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setName(name);
        jdbcProductRepository.updateProduct(id,prod);

    }

    @Override
    public void updatePrice(long id, int price) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setPrice(price);
        jdbcProductRepository.updateProduct(id,prod);
    }

    @Override
    public void updateImageUrl(long id, String url) {
        var prod = jdbcProductRepository.getProduct(id);
        prod.setImageUrl(url);
        jdbcProductRepository.updateProduct(id,prod);
    }

    @Override
    public void delete(long id) {
        jdbcProductRepository.deleteProduct(id);
    }
}
