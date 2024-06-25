package gift.model;

import gift.exception.ValidationException;

// 상품의 정보를 담는 클래스
public class Product {

    // 필드
    // Products의 map key로 id를 쓸 예정이지만, 여기에도 id가 있는 것이 편할 것 같다.
    private int id;
    private String name;
    private int price;
    private String img;

    // 기본 생성자
    public Product() {
        id = -1;
        name = null;
        price = -1;
        img = null;
    }

    // 생성자
    public Product(int id, String name, int price, String img) {
        // 모든 할당은 setter를 통해서만 하기. 생성도 마찬가지
        setId(id);
        setName(name);
        setPrice(price);
        setImg(img);
    }

    // getter는 JSON으로 바꿀 수 있도록 public으로 선언
    public int getId() {
        return id;
    }

    // setter는 사용을 폭넓은 지양하기 위해 private으로 선언
    private void setId(int id) {
        // id의 도메인 규칙 검사. setter에 넣음으로써 항상 검사하도록 함.
        verifyId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        // price의 도메인 규칙 검사.
        verifyPrice(price);
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    private void setImg(String img) {
        this.img = img;
    }

    // setter의 폭넓은 사용을 지양하기 위해 만든 구체적인 update 메서드들
    // 상품을 수정하는 경우
    public void updateAll(Product product) {
        int id = product.getId();
        String name = product.getName();
        int price = product.getPrice();
        String img = product.getImg();

        updateId(id);
        updateName(name);
        updatePrice(price);
        updateImg(img);
    }

    // 상품의 id를 수정. patch이므로 id가 -1이면 무시.
    private void updateId(int id) {
        if (id == -1) return;
        setId(id);
    }

    // 상품의 이름을 수정. patch이므로 null이면 무시.
    private void updateName(String name) {
        if (name == null) return;
        setName(name);
    }

    // 상품의 가격을 수정
    private void updatePrice(int price) {
        if (price == -1) return;
        setPrice(price);
    }

    // 상품의 이미지 주소를 수정
    private void updateImg(String img) {
        if (img == null) return;
        setImg(img);
    }

    // id 값을 검사하는 도메인 규칙
    private void verifyId(int id) {
        // 음수인 id는 불가능하다.
        if (id < 0) {
            throw new ValidationException("id는 음수일 수 없습니다.");
        }
    }

    // price 값을 검사하는 도메인 규칙
    // 비록 지금은 로직이 Id와 동일하지만, 후에 바뀔 수도 있으므로 분리
    private void verifyPrice(int price) {
        // 음수인 price는 불가능하다.
        if (price < 0) {
            throw new ValidationException("가격은 음수일 수 없습니다.");
        }
    }
}
