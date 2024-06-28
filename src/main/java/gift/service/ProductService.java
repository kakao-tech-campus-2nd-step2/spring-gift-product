package gift.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.DTO.SaveOptionDTO;
import gift.DTO.SaveProductDTO;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.getAllProduct();
        return products;
    }

    public String getJsonAllProducts(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = productRepository.getAllProduct();
        String jsonProduct="";
        try {
             jsonProduct = objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonProduct;
    }

    public void saveProduct(SaveProductDTO saveProductDTO) {
        productRepository.saveProduct(saveProductDTO);
    }

    public void saveOptions(SaveOptionDTO saveOptionDTO) {
        List<String> optionList = Arrays.stream(saveOptionDTO.getOption().split(",")).toList();
        for(String str : optionList){
            productRepository.saveOption(new SaveOptionDTO(saveOptionDTO.getId(),str));
        }

    }

    public void deleteProduct(int id) {
        productRepository.deleteProductByID(id);
    }

    public void deleteOptions(int id) {
        productRepository.deleteOptionsByID(id);
    }

    public String getProductByID(int id) {
        List<Product> products = productRepository.findProductByID(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonProduct="";
        try {
            jsonProduct = objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonProduct;
    }
}
