# spring-gift-product

<br/>

# 1단계 - 상품 API

## 요구 사항 정의

HTTP 요청과 응답은 JSON 형식으로 주고받음

데이터베이스 대신 자바 컬렉션 프레임워크를 사용

### 상품 조회

- 모든 상품 조회
    
    ex) **GET** /api/products

- 한 상품 조회

    상품 sequence로 GET
    
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

<br/>
<br/>

# 2단계 - 관리자 화면
## 요구 사항 정의

Thymeleaf로 서버 사이드 렌더링 구현

상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력

### 상품 조회

- 모든 상품을 볼 수 있는 화면
    
    ex) /admin/products

- 선택한 상품을 볼 수 있는 화면
    
    ex) /admin/products/1

### 상품 추가

- 상품 id, name, price, imageUrl을 입력할 수 있는 화면

    ex) /admin/products/new

### 상품 수정

- 선택한 상품의 name, price, imageUrl을 입력할 수 있는 화면
    
    ex) /admin/products/eidt/1

### 상품 삭제

- 별도의 화면 없이 DELETE API 호출