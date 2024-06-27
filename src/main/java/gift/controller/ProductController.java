package gift.controller;

import gift.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final Map<Long, Product> products = new HashMap();
  private long nextId = 1;

  // 상품 조회 - 특정 상품 조회
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Product product = products.get(id);
    if (product == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  // 상품 조회 - 모든 상품 조회
  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return new ResponseEntity<>(new ArrayList<>(products.values()), HttpStatus.OK);
  }

  // 상품 추가
  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product){
    product.setId(nextId++);
    products.put(product.getId(), product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  // 상품 수정
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
    if (!products.containsKey(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    updatedProduct.setId(id);
    products.put(id, updatedProduct);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }


  // 상품 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
    Product product = products.get(id);
    if (product == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    products.remove(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
