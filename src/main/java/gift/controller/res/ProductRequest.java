package gift.controller.res;

import gift.model.Product;

public record ProductRequest(Long id, String name, Integer price, String imgUrl) {

    public Product toModel(){
        return new Product(id(), name(), price(), imgUrl());
    }
}
