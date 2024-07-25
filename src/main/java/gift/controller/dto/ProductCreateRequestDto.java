package gift.controller.dto;

public record ProductCreateRequestDto(
    String name,
    Integer price,
    String imageUrl
) {
}