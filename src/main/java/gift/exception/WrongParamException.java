package gift.exception;

// 추가한 예외: url의 param이 가진 값이 잘못된 경우
public class WrongParamException extends IllegalArgumentException {
    public WrongParamException(String message) {
        super(message);
    }
}
