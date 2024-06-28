package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductDao productDao;
    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }
    private long productIdSequence = 1L;
    //조회
    @GetMapping()
    public String getAllProduct(Model model){
        model.addAttribute("products",this.productDao.findAll());
        return "products";
    }
    //추가
    @GetMapping("/add")
    public String ShowPostProduct(Model model){
        return "add";
    }
    //수정
    @GetMapping("/update/{id}")
    public String showPutProduct(@PathVariable("id") Long id,Model model) {
        Product product = productDao.selectProduct(id);
        model.addAttribute("product",product);
        return "update";
    }
    //삭제
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productDao.deleteProduct(id);
        return "redirect:/api/products";
    }
    @PostMapping()
    public String postProduct(Product product,Model model){
        long id = productIdSequence++;
        Product tmpProduct= new Product(id,product.name(),product.price(),product.imageUrl());
        productDao.insertProduct(tmpProduct);
        model.addAttribute("product",tmpProduct);
        return "redirect:/api/products";
    }
    @PostMapping("/update/{id}")
    public String putProduct(@PathVariable("id") Long id,  @ModelAttribute Product product){
        Product tmpProduct= new Product(id,product.name(),product.price(),product.imageUrl());
        productDao.updateProduct(tmpProduct);
        return "redirect:/api/products";
    }
}