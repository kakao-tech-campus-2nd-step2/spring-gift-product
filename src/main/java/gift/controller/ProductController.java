package gift.controller;

import static gift.utility.SecurityUtility.addPasswordAttribute;
import static gift.utility.SecurityUtility.verifyPassword;

import gift.model.Product;
import gift.repository.ProductDao;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller
@Controller
@RequestMapping("/products")
public class ProductController {

    // 비밀번호가 요구되므로 모든 Mapping을 POST로 사용했습니다.
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    // 제품을 추가하고 view를 반환하는 핸들러
    @PostMapping("/create")
    public String createProduct(@RequestParam(name = "password") String password,
        @RequestParam(name = "id") long id, @RequestParam(name = "name") String name,
        @RequestParam(name = "price") int price, @RequestParam(name = "image") String image,
        Model model) {
        // 비밀번호 검증
        verifyPassword(password);

        // 객체 준비
        Product product = new Product(id, name, price, image);
        // 제품을 추가
        productDao.insertProduct(product);

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(model);

        return "html/manager";
    }

    // 전체 상품을 담은 view를 반환하는 핸들러
    // 원래 Post로 해야 하는데, 화면을 보기 위해 Get으로 선언했습니다.
    @GetMapping("/read")
    public String readProducts(@RequestParam(name = "password") String password, Model model) {
        // 비밀번호에 대한 검증부터
        verifyPassword(password);

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(model);

        return "html/manager";
    }

    // id가 i인 상품을 수정하는 핸들러
    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable(name = "id") long targetId,
        @RequestParam(name = "password") String password,
        @RequestParam(name = "id") String id, @RequestParam(name = "name") String name,
        @RequestParam(name = "price") String price, @RequestParam(name = "image") String image,
        Model model) {
        // 비밀번호 검증
        verifyPassword(password);

        // 수정한 정보를 담은 객체 생성.
        // 빈 문자열을 받으면 수정하지 않는 기능을 살리기 위해 특수한 생성자 사용
        Product product = new Product(id, name, price, image);

        // 제품 수정
        productDao.updateProduct(targetId, product);

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(model);

        return "html/manager";
    }

    // id가 i인 상품을 삭제하는 핸들러
    @PostMapping("/{id}/delete")
    public String deleteProduct(@RequestParam(name = "password") String password,
        @PathVariable(name = "id") long id, Model model) {
        // password 검증
        verifyPassword(password);

        // 하나의 제품 제거
        productDao.deleteProduct(id);

        // html로 넘길 attributes를 넣기
        addAttributesForManagerPage(model);

        return "html/manager";
    }

    // 모든 상품을 삭제하는 핸들러
    @PostMapping("/delete")
    public String deleteAllProducts(@RequestParam(name = "password") String password, Model model) {
        // password 검증
        verifyPassword(password);

        // 모든 제품 제거
        productDao.deleteAllProduct();

        // html로 넘길 attributes를 넣기 (사실 없어도 됨)
        addAttributesForManagerPage(model);

        return "html/manager";
    }

    // manager.html에서 보여줄 attributes를 넣는 함수
    private void addAttributesForManagerPage(Model model) {
        // 제품 목록 넣어줌
        List<Product> productsList = productDao.selectProduct();
        model.addAttribute("products", productsList);

        // 비밀번호를 최초만 맞추면 여기서부터는 계속 자동으로 넣어줌
        addPasswordAttribute(model);
    }
}
