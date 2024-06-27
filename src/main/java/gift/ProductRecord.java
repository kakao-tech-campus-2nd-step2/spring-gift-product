package gift;

public record ProductRecord(long id, String name, int price, String imageUrl) {
    public ProductRecord getUpdatedRecord(ProductRecord patch) {
        String newName = name;
        if (patch.name != null) {
            newName = patch.name;
        }

        int newPrice = price;
        if (patch.price != 0) {
            newPrice = patch.price;
        }

        String newImageUrl = imageUrl;
        if (patch.imageUrl != null) {
            newImageUrl = patch.imageUrl;
        }
        return new ProductRecord(id, newName, newPrice, newImageUrl);
    }
}
