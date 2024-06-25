# spring-gift-product
- 상품을 조회, 추가 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청, 응답은 JSON 형식으로 주고받는다.
- 별도 DB 없이 적절한 자바 컬렉션 프레임워크를 사용하요 저장한다.

## 기능 요구사항 명세
### 상품 추가
- 입력받은 payload에 맞는 product 객체를 생성하여 저장.
```
POST /api/products/{product-id}
```

### 상품 조회
- 모든 상품 정보들의 리스트를 JSON으로 반환.
```
GET /api/products
```

### 상품 수정
- payload에 맞게 상품의 정보를 수정.
```
PATCH /api/products/{product-id}
```

### 상품 삭제
- 명시된 id에 부합하는 상품을 삭제.
```
DELETE /api/products/{product-id}
```
