# spring-gift-product
## 기능 요구 사항
* 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
* HTTP 요청과 응답은 JSON 형식으로 주고받는다.
* 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.


**Request**
```
GET /api/products HTTP/1.1
```

**Response**
```
HTTP/1.1 200 
Content-Type: application/json
[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
]
```

**상품 데이터 관리**
```java
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
}

public class Product {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    // ...
}
```

### 1. get
* 모든 product 조회
  * /api/products
* id로 product 선택 조회
  * /api/products/:id
  * 없으면 error 발생
### 2. post
* product 추가
  * /api/products
### 3. put
* product 수정
  * /api/products/:id
  * 없으면 error 발생
### 4. delete
* product 삭제
  * /api/products/:id
  * 없으면 error 발생

  # step 2
### /api/products/admin
상품 관리
### /api/products/admin/add
상품 추가
### /api/products/admin/:id
상품 편집 및 삭제 가능
  
