package gift.restapi.dto.request;

public record ProductCreateRequest(
        String name,
        Integer price,
        String imageUrl
) {
}
