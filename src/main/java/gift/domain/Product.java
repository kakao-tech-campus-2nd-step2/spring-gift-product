package gift.domain;

public record Product(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {

}
