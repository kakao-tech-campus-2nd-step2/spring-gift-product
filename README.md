# 요구 사항 정리(STEP 01)
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
---

# 요구 사항 정리(STEP 02)
## 사용 도구
- SSR을 위해 타임리프 사용
## 상품 보여주기
- 저장되어있는 상품들 테이블형식으로 속성들까지 보여주기
## 상품 추가
- 메인 페이지에서 상품 추가 버튼 구현
  - 버튼 누르면 상품 추가 전용 페이지로 라우팅
- 상품정보를 입력하고 추가를 하면 memory DB에 저장
- 저장후, 상품 보여주기 페이지로 redirect리
- 예외사항: 이미 사용중인 id의 상품을 추가하려할때
## 상품 삭제
- 개별 삭제 기능
  - 테이플 내부에 삭제 버튼 구현
    - 삭제 버튼 클릭시, memory DB에서 해당 상품 삭제되고, 상품 보여주기 페이지로 redirect
- 다중 삭제 기능
  - 테이블 내부에 체크박스 기능 구현
    - 체크를 하고 일괄 삭제 버튼 클릭시 체크 상품들 삭제되고, 상품 보여주기 페이지로 redirect
## 상품 수정
- 테이블 내부에 수정 버튼 구현
  - 수정 버튼 클릭시, 수정 전용 페이지로 라우팅 후 수정할거 수정하고 상품 보여주기 페이지로 redirect
