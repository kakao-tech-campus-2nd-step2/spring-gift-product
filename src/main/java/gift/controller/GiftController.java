package gift.controller;

import gift.dto.ProductDTO;
import jakarta.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")
public class GiftController {

    final Map<Long,ProductDTO> map = new HashMap<>();
//    Long id = 1L;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id){
        if (map.containsKey(id)){
            ResponseEntity.ok(map.get(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        if (!map.isEmpty()){
            return ResponseEntity.ok(map.values().stream().collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> postProducts(@RequestBody ProductDTO productDTO){
        if (map.containsKey(productDTO.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        map.put(productDTO.getId(),productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/products/")
    public ResponseEntity<ProductDTO> putProduct(@RequestBody ProductDTO productDTO) {
        if (!map.containsKey(productDTO.getId())) {
            return ResponseEntity.notFound().build();
        }
        map.put(productDTO.getId(), productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> deleteProducts(@RequestParam Long id){
        if (map.containsKey(id)){
            map.remove(id);
            ResponseEntity.ok("delete");
        }
        return ResponseEntity.notFound().build();
    }
}
