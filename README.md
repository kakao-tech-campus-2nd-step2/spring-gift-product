# spring-gift-product

## 1단계(상품 API) 요구 사항

### 과제 진행 요구 사항

* 미션은 선물하기 상품 관리 저장소를 포크하고 클론하는 것으로 시작한다.
* 온라인 코드 리뷰 요청 1단계 문서를 참고하여 실습 환경을 구축한다.
	1. 미션 시작 버튼을 클릭하여 미션을 시작한다.
	2. 저장소에 GitHub 사용자 이름으로 브랜치가 생성되었는지 확인한다.
	3. 저장소를 내 계정으로 포크한다.
* 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
* Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
	* AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.



### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

* HTTP 요청과 응답은 JSON 형식으로 주고받는다.
* 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

#### 예시

**Request**

```http
GET /api/products HTTP/1.1
```

**Response**

```http
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



### 1단계 기능 목록

> 패키지 분리 없이 단일 패키지로 진행. 단계를 진행하며 패키지 분리 리팩토링할 지 고려

**Product 클래스**

상품의 정보를 저장하는 객체

* 필드: `String id`, `String name`, `int price`, `String imageUrl`
* 메서드
  * `public Product(String id, String name, int price, String imageUrl)`



**ProductController 클래스 @RestController**

상품 조회, 추가, 수정, 삭제를 처리하는 엔드포인트 정의

* 엔드포인트
  * `GET /api/products`: 모든 상품 조회
  * `GET /api/products?id={id}`: id를 통해 상품 조회
  * `POST /api/products`: JSON을 통해 새로운 상품 추가
  * `PUT /api/products?id={id}`: 특정 id의 상품 수정
  * `DELETE /api/products?id={id}`: 특정 id의 상품 삭제



**ProductService 클래스**

상품 조회, 추가, 수정, 삭제 기능을 실질적으로 처리하는 클래스

* 필드: `private final Map<Long, Product> products`: 상품 정보를 저장하는 저장소. <id, 상품>의 HashSet

* 메서드
  * `public List<Product> getAllProducts()`: 모든 상품 조회
  * `public Product getProductById(String id)`: `id`를 통해 상품 조회
  * `public createProduct(Product product)`: 새로운 상품 추가
    * JSON 형식이 이상할 경우 에러 발생
  * `public Product updateProduct(String id, Product product)`: id를 통해 상품을 검색한 후 상품 수정
    * 존재하지 않는 상품인 경우 예외 발생
  * `public void deleteProduct(String id)`: 특정 id의 상품 삭제
    * 존재하지 않는 상품인 경우 예외 발생
