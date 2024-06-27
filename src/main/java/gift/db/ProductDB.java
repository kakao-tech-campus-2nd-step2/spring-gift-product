package gift.db;

import gift.dto.Product;

import java.util.List;

public interface ProductDB {
    //추가
    void addProduct(Product product);

    //읽기
    Product getProduct(Long id);

    List<Product> getProducts();

    //삭제
    void removeProduct(Long id);

    void removeProducts(List<Long> productIds);

    //수정
    void editProduct(Product product);
}
