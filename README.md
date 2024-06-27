# spring-gift-product

## step1 - 조회, 추가, 수정, 삭제 API

- [x] **상품 조회**
  - 상품 목록을 조회하는 기능
  - HTTP 메서드: GET
  - 엔드포인트: `/api/products`

- [x] **상품 추가**
  - 새로운 상품을 추가하는 기능
  - HTTP 메서드: POST
  - 엔드포인트: `/api/products`

- [x] **상품 수정**
  - 기존 상품 정보를 수정하는 기능
  - HTTP 메서드: PUT
  - 엔드포인트: `/api/products/{id}`

- [x] **상품 삭제**
  - 특정 ID를 가진 상품을 삭제하는 기능
  - HTTP 메서드: DELETE
  - 엔드포인트: `/api/products/{id}`

## step2 - 관리자 화면

- [x] **상품 목록 화면**
  - 상품 목록을 화면에 표시하는 기능
  - HTML 페이지: `templates/products.html`
  - 접속 방법 : `localhost:8080/products`
  - 상품 목록과 오른쪽 상단의 상품 추가 버튼
  - 각 상품 우측 상품 수정 버튼과 상품 삭제 버튼

- [x] **상품 추가 화면**
  - 새로운 상품을 추가하는 화면
  - HTML 페이지: `templates/product_form.html`
  - 폼을 통해 상품 정보를 입력받아 추가

- [x] **상품 수정 화면**
  - 기존 상품 정보를 수정하는 화면
  - HTML 페이지: `templates/product_edit_form.html`
  - 폼을 통해 상품 정보를 입력받아 수정

- [x] **AJAX를 통한 비동기 처리**
  - 상품 추가, 수정, 삭제 시 페이지 새로고침 없이 비동기로 처리
  - `fetch` API를 사용하여 서버와 통신

## step3 - JDBC 적용 및 리팩토링

- [ ] **데이터베이스 설정**
  - `application.properties` 수정
- [ ] **JDBC DAO 구현**
  - `ProductDao` 클래스 구현
  - `Product` 테이블 생성
- [ ] **컨트롤러 리팩토링**
  - 기존의 `HashMap` 대신 데이터베이스를 사용하도록 `ProductController` 수정
  - 상품 추가, 조회, 수정, 삭제 시 데이터베이스에서 처리되도록 로직 수정
- [ ] **테스트코드 수정**
  - 데이터베이스 설정에 맞게 기존 테스트코드 수정
  