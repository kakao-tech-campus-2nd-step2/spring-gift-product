package gift.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;
import java.util.List;


@Controller
@RequestMapping("/")
public class ProductController {
    private JdbcTemplate jdbcTemplate;

    public ProductController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("api/products")
    public String getAllProducts(Model model) {
        String sql = "select id, name, price, imageurl from products";
        List<Product> products = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getLong("price"),
                            resultSet.getString("imageurl")
                    );
                    return product;
                });
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        String sql = "INSERT INTO products(id, name, price, imageurl) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());

        return "redirect:/api/products";
    }

    @PostMapping("/api/products/{id}")
    public ResponseEntity<ProductDTO> modifyProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        if (!id.equals(productDTO.id())) {
            String deleteSql = "DELETE FROM products WHERE id = ?";
            jdbcTemplate.update(deleteSql, id);

            String insertSql = "INSERT INTO products (id, name, price, imageurl) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertSql, productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
        }

        String updateSql = "UPDATE products SET id = ?, name = ?, price = ?, imageurl = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl(), id);

        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("api/products/{id}")
    public String DeleteProduct(@PathVariable("id") Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);

        return "redirect:/api/products";
    }
}