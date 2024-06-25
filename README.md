# spring-gift-product

## 요구 사항 정의

HTTP 요청과 응답은 JSON 형식으로 주고받음

데이터베이스 대신 자바 컬렉션 프레임워크를 사용

### 상품 조회

- 모든 상품 조회
    
    ex) **GET** /api/products

- 한 상품 조회

    상품 id로 GET
    
    ex) **GET** /api/products/1
    

### 상품 추가

- 상품 id, name, price, imageUrl로 POST
    
    ex) **POST** /api/products
    

### 상품 수정

- 상품 id와 name, price, imageUrl로 PUT
    
    ex) **PUT** /api/products/1
    

### 상품 삭제

- 상품 id로 DELETE

    ex) **DELETE** /api/products/1