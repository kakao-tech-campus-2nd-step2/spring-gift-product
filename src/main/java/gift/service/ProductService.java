package gift.service;

import gift.DB.ProductDB;
import gift.DTO.ProductDTO;
import gift.domain.Product;
import gift.domain.Product.ProductSimple;
import gift.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    private ProductDB productDB;
    @Autowired
    private ProductMapper productMapper;

    public List<ProductSimple> getProductList() {
        List<ProductSimple> list = new ArrayList<>();

        for (ProductDTO p : productDB.getList()) {
            list.add(new ProductSimple(p.getId(), p.getName()));
        }

        return list;
    }

    public ProductDTO getProduct(Long id) {
        if (!productDB.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        return productDB.getProduct(id);
    }

    public void createProduct(Product.CreateProduct create) {
        long index = productDB.getLastIndex();
        productDB.setProduct(index, productMapper.createProduct(index, create));
    }

    public void updateProduct(Product.UpdateProduct update, Long id) {
        if (!productDB.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        productDB.updateProduct(id, productMapper.updateProduct(productDB.getProduct(id), update));
    }

    public void deleteProduct(Long id) {
        if (!productDB.validateId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        productDB.removeProduct(id);
    }

}
