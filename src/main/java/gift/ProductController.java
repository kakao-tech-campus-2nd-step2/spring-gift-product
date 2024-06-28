package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final JdbcTemplate jdbcTemplate;

    public ProductController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("Url")
            );
        }
    };

    // 모든 상품을 보여주는 페이지
    //쿼리로 모든 상품을 조회하여 모델에 추가한 후, products.html Thymeleaf 템플릿으로 렌더링
    @GetMapping
    public String AllProducts(Model model) {
        String sql = "SELECT id, name, price, url FROM products";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper);
        model.addAttribute("products", products);
        return "Products";
    }

    // 상품 추가 폼 페이지
    // 빈 Product 객체를 모델에 추가해서 Add_product.html Thymeleaf 템플릿으로 렌더링
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "Add_product";
    }

    // 실제 상품 추가 처리
    //폼에서 전송된 Product 객체를 데이터베이스에 추가한 후, 상품 목록 페이지로 바로가기(html실행)
    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        String sql = "INSERT INTO products (id, name, price, url) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
        return "redirect:/admin/products";
    }

    // 상품 수정 폼 페이지
    //쿼리로 특정 ID의 상품을 조회하여 모델에 추가한 후, edit_product.html Thymeleaf 템플릿으로 렌더링
    @GetMapping("/edit/{id}")
    public String EditProductForm(@PathVariable Long id, Model model) {
        String sql = "SELECT id, name, price, url FROM products WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, productRowMapper);
        model.addAttribute("product", product);
        return "Edit_product";
    }

    // 실제 상품 수정 처리
    //수정된 Product 객체를 데이터베이스에 업데이트한 후, 상품 목록 페이지로 바로가기
    @PostMapping("/update/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return "redirect:/admin/products";
    }

    // 상품 삭제 처리
    //특정 ID의 상품을 데이터베이스에서 삭제한 후, 상품 목록 페이지로 바로가기
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "redirect:/admin/products";
    }
}