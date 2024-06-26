package gift.model;

publici class Product {
    private final Long id;
    private final String name;
    private final int price;
    private final String imageUrl;

    private Product(Builder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.price=builder.price;
        this.imageUrl=builder.imageUrl;
    }
    public Long getId() {return id;}
    public String getName() {return name;}
    public int getPrice() {return price;}
    public String getImageUrl() {return imageUrl;}

    public static class Builder {
        private Long id;
        private String name;
        private int price;
        private String imageUrl;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder price(int price) {
            this.price = price;
            return this;
        }
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        public Product build() {
            return new Product(this);
        }
    }
}