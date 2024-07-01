# spring-gift-product
## 제품 정보를 관리하는 간단한 애플리케이션
H2 데이터베이스를 사용하여 제품 정보를 저장하며, RESTful API를 통해 제품 데이터를 관리할 수 있습니다.

### 기능 
- 제품에 대한 CRUD 작업 (생성, 조회, 수정, 삭제)
- H2 데이터베이스 초기화 및 샘플 데이터 삽입
- 제품 관리를 위한 RESTful API 제공
- H2 데이터베이스 콘솔을 통한 데이터베이스 관리

### REST API 사용법
Postman 또는 브라우저를 사용하여 다음 REST API 엔드포인트를 테스트할 수 있습니다:

- 모든 제품 조회: GET /products
- 특정 제품 조회: GET /products/{id}
- 제품 생성: POST /products
Body (JSON):

{
"name": "New Product",
"price": 15.99,
"description": "Description for new product"
}

제품 수정: PUT /products/{id}
Body (JSON):
{
"name": "Updated Product",
"price": 18.99,
"description": "Updated description"
}
제품 삭제: DELETE /products/{id}