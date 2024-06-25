# spring-gift-product
## 기능 요구사항
1. 상품 정보를 저장할 수 있어야 함
   1. 상품 1개의 정보를 저장할 객체가 있어야 함
   2. 상품 객체들을 저장할 공간이 있어야 함
2. 새로운 상품 정보를 추가할 수 있어야 함
3. 상품을 조회할 수 있어야 함
   1. 상품 목록을 조회할 수 있어야 함
   2. 개별 상품을 조회할 수 있어야 함
4. 상품 정보를 수정할 수 있어야 함
5. 상품 정보를 삭제할 수 있어야 함


## API 명세
### 상품 정보 추가
#### Request
```
POST /api/products

{
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
#### Response
```
HTTP/1.1 200 
Content-Type: application/json

{
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```

### 전체 상품 정보 조회
#### Request
```
GET /api/products
```
#### Response
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

### 단일 상품 정보 조회
#### Request
```
GET /api/products/{id}
```
#### Response
조회 성공
```
HTTP/1.1 200 
Content-Type: application/json

{
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
존재하지 않는 id
```
HTTP/1.1 400
```

### 상품 정보 수정
#### Request
```
PATCH /api/products/{id}

{
    "price": 5000
}
```
#### Response
수정 성공
```
HTTP/1.1 200 
Content-Type: application/json

{
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 5000,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
}
```
존재하지 않는 id
```
HTTP/1.1 400
```

### 상품 정보 삭제
#### Request
```
DELETE /api/products/{id}
```
#### Response
삭제 성공
```
HTTP/1.1 200
```
존재하지 않는 id
```
HTTP/1.1 400
```