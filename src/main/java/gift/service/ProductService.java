package gift.service;

import gift.dto.CreateProduct;
import gift.dto.EditProduct;
import gift.dto.ProductDTO;
import gift.dto.ProductDetailDTO;
import gift.entity.Product;
import gift.repository.CollectionDB;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private final CollectionDB collectionDB;

    public ProductService(CollectionDB collectionDB) {
        this.collectionDB = collectionDB;
    }

    public Map<Long,ProductDTO> getAllProducts() {
        Map<Long,Product> allProducts = collectionDB.findAll();
        Map<Long,ProductDTO> allProductsDTO= new HashMap<>();
        for (Long key : allProducts.keySet()) {
            Product product = allProducts.get(key);
            ProductDTO productDTO = new ProductDTO(product.getName(),product.getPrice(),product.getUrl());
            allProductsDTO.put(key,productDTO);
        }
        return allProductsDTO ;
    }

    public void createProduct(CreateProduct.Request request) {
        Product product = new Product(request.getName(),request.getPrice(), request.getImageUrl());
        collectionDB.saveProduct(request.getId(), product);
    }

    public ProductDetailDTO getProductDetail(Long id) {
        return ProductDetailDTO.fromEntity(collectionDB.getProducts().get(id));
    }

    public ProductDetailDTO editProductDetail(Long id, EditProduct.Request request) {
        Product product = collectionDB.getProducts().get(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setUrl(request.getImageUrl());
        return ProductDetailDTO.fromEntity(product);
    }
}
