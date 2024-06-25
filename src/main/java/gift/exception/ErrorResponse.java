package gift.exception;

// JSON 형태로 에러 내용을 반환하기 위해 만든 클래스
public class ErrorResponse {
    // 에러 종류
    private String message;
    // 자세한 에러 메시지
    private String details;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

