package gift.domain;

import java.util.HashMap;
import java.util.Map;

public class Products {

    private static Products products;
    private final HashMap<Long,Product> productsList;
    public Map<Long, Product> getProducts() {
        return productsList;
    }

    public boolean add(Product product) {
        if(product.getId() !=null){
            productsList.put(product.getId(), product);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        //간단한 유효성 검증. 삭제하려는 id값이 존재하지 않으면 false, 삭제를 올바르게 마쳤다면 true를 리턴한다.
        return productsList.remove(id) != null;
    }

    public boolean edit(Long id, Product product) {

        //간단한 유효성 검증. id값이 존재하지 않으면 false를 리턴한다
        if (productsList.remove(id) != null) {
            productsList.put(product.getId(), product);
            return true;
        }

        return false;
    }

    private Products() {
        productsList = new HashMap<>();
    }

    public static Products getInstance(){
        if(products==null){
            products=new Products();
        }
        return products;
    }
}

