# spring-gift-product
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

## 기능 목록

### 1. 프로젝트 구조 설정
- 상품 모델 클래스(model/Product) 생성
- 상품 데이터 요청(dto/ProductRequestDto) 클래스 생성
- 상품 서비스 클래스(service/ProductService) 생성
- 상품 컨트롤 클래스(controller/ProductController) 생성

### 2. 상품 조회 기능
- 모든 상품 조회: GET '/api/products'
- 특정 상품 조회: GET '/api/products/{id}'

### 3. 상품 추가 기능
- 새 상품 추가: POST '/api/products'

### 4. 상품 수정 기능
- 기존 상품 수정: PUT '/api/products/{i햣d}'

### 5. 상품 삭제 기능
- 특정 상품 삭제: DELETE '/api/products/{id}