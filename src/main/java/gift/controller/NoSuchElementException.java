package gift.controller;

public class NoSuchElementException extends RuntimeException {

    private final Long productId;

    public NoSuchElementException(Long productId) {
        super("Product not found with id: " + productId);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
