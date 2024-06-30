package gift.controller.DTO;

public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public Long getId() {
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
}
