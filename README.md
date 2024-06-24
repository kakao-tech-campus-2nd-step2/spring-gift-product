# spring-gift-product
## 기능 요구 사항 정리
상품을 조회, 추가, 수정, 삭제
- HTTP 요청 및 응답
  - FindProductController
  - AddProductController
  - CorrectProductController
  - DeleteProductController
- 자바 컬렉션 프레임워크를 사용한 데이터 저장
  - ProductController
    - Map<Long, Product> products