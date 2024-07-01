package gift.controller.dto;

public record ProductUpdateRequestDto(
    String name,
    Integer price,
    String imageUrl
) {

}
