package gift;

public record ProductCreateRequest(
        String name,
        Integer price,
        String imageUrl
) {
}
