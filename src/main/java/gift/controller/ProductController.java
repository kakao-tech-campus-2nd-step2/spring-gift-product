package gift.controller;

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

import gift.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private Map<Long, Product> products = new HashMap<>();

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> productList = new ArrayList<>(products.values());
		return ResponseEntity.ok(productList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
		Product product = products.get(id);
		if(product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product request){
		products.put(request.getId(), request);
		return new ResponseEntity<>(request, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product request){
		if(!products.containsKey(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		request.setId(id);
		products.put(id, request);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id){
		if(!products.containsKey(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		products.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
