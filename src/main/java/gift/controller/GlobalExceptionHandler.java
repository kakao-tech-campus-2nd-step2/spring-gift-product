package gift.controller;

import gift.exception.ErrorResponse;
import gift.exception.ValidationException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 전역 예외 핸들러.
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // ValidationException 핸들러 함수
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handler(ValidationException validationException) {
        // 어떤 에러가 발생했는지.
        String message = "validation error";
        // 예외의 message를 통해 자세한 내용을 가져옴.
        String details = validationException.getMessage();
        // ErrorResponse 객체를 만듦
        var errorResponse = new ErrorResponse(message, details);
        // ErrorResponse객체를 JSON으로 바꾼 것을 반환하면서 BAD_REQUEST(400)를 반환.
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // NoSuchElement 핸들러 함수
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handler(NoSuchElementException noSuchElementException) {
        // 어떤 에러가 발생했는지.
        String message = "no such element error";
        // 예외의 message를 통해 자세한 내용을 가져옴.
        String details = noSuchElementException.getMessage();
        // ErrorResponse 객체를 만듦
        var errorResponse = new ErrorResponse(message, details);
        // ErrorResponse객체를 JSON으로 바꾼 것을 반환하면서 NOT_FOUND(404)를 반환.
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
