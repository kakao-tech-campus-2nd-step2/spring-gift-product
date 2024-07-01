package gift.controller;

import static gift.utility.SecurityUtility.addPasswordAttribute;

import gift.exception.ValidationException;
import gift.exception.WrongParamException;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역 예외 핸들러.
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    // ValidationException 핸들러 함수
    @ExceptionHandler(ValidationException.class)
    public String handler(ValidationException validationException, Model model) {
        // 에러 메시지 받아옴
        String message = validationException.getMessage();

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(message, model);

        return "html/error";
    }

    // NoSuchElement 핸들러 함수
    @ExceptionHandler(NoSuchElementException.class)
    public String handler(NoSuchElementException noSuchElementException, Model model) {
        // 에러 메시지 받아옴
        String message = noSuchElementException.getMessage();

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(message, model);

        return "html/error";
    }

    // WrongParamException 핸들러 함수
    @ExceptionHandler(WrongParamException.class)
    public String handler(WrongParamException wrongParamException, Model model) {
        // 에러 메시지 받아옴
        String message = wrongParamException.getMessage();

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(message, model);

        return "html/error";
    }

    // error.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForManagerPage(String message, Model model) {
        // 에러 메시지 넣어줌
        model.addAttribute("message", message);

        // 비밀번호를 최초만 맞추면 여기서부터는 계속 자동으로 넣어줌
        addPasswordAttribute(model);
    }
}
