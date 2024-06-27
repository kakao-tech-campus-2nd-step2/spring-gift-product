# spring-gift-product
## 기능 요구 사항 정리
### step 1
상품을 조회, 추가, 수정, 삭제
- HTTP 요청 및 응답
  - getProduct()
  - getAllProduct()
  - addProduct()
  - updateProduct()
  - deleteProduct()
- 자바 컬렉션 프레임워크를 사용한 데이터 저장
  - ProductRepository
    - Map<Long, Product> products
---
### step 2
Thymeleaf 기반 서버 사이드 렌더링 관리자 화면 구현
- 메인페이지 -> 현재 상품리스트 나열
  - add, delete, update 버튼