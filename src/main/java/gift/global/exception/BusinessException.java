package gift.global.exception;

import gift.global.response.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * RuntimeException 을 상속받는 커스텀 에러
 * 개발자가 직접 날리는 에러
 */
public class BusinessException extends RuntimeException{
    HttpStatus status;

    public BusinessException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
