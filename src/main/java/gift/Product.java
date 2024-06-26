package gift;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Arrays;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {
    int id;
    String name;
    int price;
    String imageUrl;
    List<String> options;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getOptions() {
        return options;
    }

    public Product(int id, String name, int price, String imageUrl,String options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.options = Arrays.stream(options.split(",")).toList();
    }
}
