package gift.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ProductException {
    private ProductNotFoundException(final Long id) {
        super(String.format("Can't find Product with id %s", id.toString()), HttpStatus.NO_CONTENT);
    }

    public static ProductNotFoundException of(final Long id) {
        return new ProductNotFoundException(id);
    }
}
