package gift.dto;

//import com.sun.istack.NotNull;

public class EditProduct {



    public static class Request{
        //@NotNull
        private String name;
        //@NotNull
        private int price;
        //@NotNull
        private String imageUrl;

        public Request( String name, int price,String imageUrl) {

            this.name=name;
            this.price=price;
            this.imageUrl=imageUrl;
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
