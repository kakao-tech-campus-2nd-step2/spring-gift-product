package gift.service;

import gift.dto.CreateProduct;
import gift.entity.Product;
import gift.repository.CollectionDB;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final CollectionDB collectionDB;

    public ProductService(CollectionDB collectionDB) {
        this.collectionDB = collectionDB;
    }

    public void createProduct(CreateProduct.Request request) {
        Product product = new Product(request.getName(),request.getPrice(), request.getImageUrl());
        collectionDB.saveProduct(request.getId(), product);
    }

}
