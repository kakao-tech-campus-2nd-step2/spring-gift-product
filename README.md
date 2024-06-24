# 요구 사항 정리
## 상품 등록
- 상품요소에는 id, name, price, imageUrl이 포함된다.
- POST 방식의 메서드 사용 할 것.
- `private final Map<Long, Product> products = new HashMap<>();`을 활용해서 입력된 Product를 저장
  - products.put() 메서드 사용
```
// Request:
POST localhost:8080/api/products

//Body payload:
{
    "id": 1,
    "name": "콜라",
    "price": 2000,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}

```

## 상품 보기
- GET 방식의 메서드 사용 할 것.
- `private final Map<Long, Product> products = new HashMap<>();`에 저장된 Product들 리스트로 반환.
- List 구조로 Product들의 정보를 JSON으로 반환 할 것.
```
// Request:
GET localhost:8080/api/products
```

## 상품 수정
- PUT 방식의 메서드 사용 할 것.
- `private final Map<Long, Product> products = new HashMap<>();`를 활용
  - products.put() 메서드 사용
- id로 제품을 식별해서 수정하기.
```
PUT localhost:8080/api/products/{id}

// Body payload:
{
  id: 123, 
  name: 9, 
  price: "love the service",
  imageUrl: "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"

}
```
## 상품 삭제
- DELETE 방식의 메서드 사용 할 것.
- `private final Map<Long, Product> products = new HashMap<>();`을 활용
  - products.remove() 메서드 사용
- id로 제품을 식별해서 삭제하기.
```
DELETE localhost:8080/api/products/{id}
```