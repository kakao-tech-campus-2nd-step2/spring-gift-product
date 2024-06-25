package gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    private final Map<Long, ProductDTO> producsts = new HashMap<>();

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        if (producsts.containsKey(id)){
            return producsts.get(id);
        }
        return null;
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProduct(){
        if (!producsts.isEmpty()){
            return producsts.values().stream().collect(Collectors.toList());
        }
        return null;
    }

    @PostMapping("/products")
    public ProductDTO postProduct(@RequestBody ProductDTO productDTO){
        if (producsts.containsKey(productDTO.getId())){
            return null;
        }
        producsts.put(productDTO.getId(),productDTO);
        return productDTO;
    }

    @PutMapping("/products")
    public ProductDTO putProduct(@RequestBody ProductDTO productDTO) {
        if (producsts.containsKey(productDTO.getId())) {
            producsts.put(productDTO.getId(), productDTO);
            return productDTO;
        }
        return null;
    }

    @DeleteMapping("/products")
    public String deleteProducts(@RequestParam Long id){
        if (producsts.containsKey(id)){
            producsts.remove(id);
            return "Ok";
        }
        return "Fail";

    }
}
