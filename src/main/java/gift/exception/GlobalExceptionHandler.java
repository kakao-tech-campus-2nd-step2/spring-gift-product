package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 서버에서 발생하는 예외를 종합적으로 처리하는 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public String handleProductAlreadyExistsException(ProductAlreadyExistsException e, Model model) {
        model.addAttribute("errorMessage", "Product already exists with the same name, price, and imageUrl.");
        return "productDuplicate";
    }
}
