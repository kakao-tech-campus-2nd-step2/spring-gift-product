package gift.controller;

import static gift.utility.SecurityUtility.addPasswordAttribute;
import static gift.utility.SecurityUtility.verifyPassword;

import gift.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// manager.html에서 다른 페이지로 전환할 때 사용하는 컨트롤러
@Controller
@RequestMapping("/page")
public class PageController {

    // edit-product.html을 SSR로 넘겨주는 핸들러
    @PostMapping("/edit")
    public String showEditPage(@RequestParam(name = "password") String password,
        @RequestParam(name = "id") long id, @RequestParam(name = "name") String name,
        @RequestParam(name = "price") int price, @RequestParam(name = "image") String image,
        Model model) {
        // 비밀번호 검증
        verifyPassword(password);

        // 수정해야 할 정보를 담은 객체 생성
        Product product = new Product(id, name, price, image);

        // html로 넘길 attributes를 넣기
        addAttributesForEditPage(product, model);

        return "html/edit-product";
    }

    // edit-product.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForEditPage(Product product, Model model) {
        // 수정해야 할 제품 넣어줌
        model.addAttribute("product", product);

        // 비밀번호를 최초만 맞추면 여기서부터는 계속 자동으로 넣어줌
        addPasswordAttribute(model);
    }
}
