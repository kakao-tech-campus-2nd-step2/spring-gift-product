package gift.dto;

//import com.sun.istack.NotNull;

public class CreateProduct {

    public static class Response{
        //@NotNull
        private Long id;
        //@NotNull
        private String name;
        //@NotNull
        private int price;
        //@NotNull
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

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


    }

    public static class Request{
        //@NotNull
        private Long id;
        //@NotNull
        private String name;
        //@NotNull
        private int price;
        //@NotNull
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

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
