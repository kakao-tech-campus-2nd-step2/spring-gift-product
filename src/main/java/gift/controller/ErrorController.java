package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    // 오류 페이지로 매핑
    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
