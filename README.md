# spring-gift-product

## 구현해야 할 기능
- [x] 상품 전체 조회
- [x] 상품 추가
  - [x] 상품 마다 고유한 ID 부여
- [x] 상품 수정
- [x] 상품 삭제
- [x] 테스트 코드


## API 문서
### 상품 전체 조회
- Request
```http
GET /api/products HTTP/1.1
```
- Response
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

### 상품 추가
- Request
```http
POST /api/products HTTP/1.1
Content-Type: application/json

{
  "name": "아이스 카페 아메리카노 T",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
- Response
```http
HTTP/1.1 201
Content-Type: application/json

{
  "id": 8146027,
  "name": "아이스 카페 아메리카노 T",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
### 상품 수정
- Request
```http
PUT /api/products/{id} HTTP/1.1
Content-Type: application/json

{
  "name": "아이스 카페 아메리카노 T",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
- Response
```http
HTTP/1.1 200
Content-Type: application/json

{
  "id": 8146027,
  "name": "아이스 카페 아메리카노 T",
  "price": 4500,
  "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
### 상품 삭제
- Request
```http
DELETE /api/products/{id} HTTP/1.1
```
- Response
```http
HTTP/1.1 200
Content-Type: application/json

{
  "id": 8146027
}
```