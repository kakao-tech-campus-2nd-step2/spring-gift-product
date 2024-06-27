package gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // HttpRequestMethodNotSupportedException: Request method 'POST' is not supported <- 발생에 대한 대응
    // HTML에서 input 태그의 속성중 type = hidden, name = _method, value = put or delete를 전달하기 위한 빈
    // 이를 추가하지 않으면 put, delete가 아닌 모두 post나 get으로 전달되게 되어 mapping 할 수 없다
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
