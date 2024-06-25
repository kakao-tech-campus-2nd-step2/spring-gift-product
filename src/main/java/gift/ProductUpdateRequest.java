package gift;

public record ProductUpdateRequest(
        String name,
        Integer price,
        String imageUrl
) {
}
