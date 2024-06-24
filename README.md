# spring-gift-product
## 기능 요구 사항 정리
상품을 조회, 추가, 수정, 삭제
- HTTP 요청
  - requestFindProduct()
  - requestAddProduct()
  - requestCorrectProduct()
- HTTP 응답
  - responseFindProduct()
  - responseAddProduct()
  - responseCorrectProduct()
  - responseDeleteProduct()
- 자바 컬렉션 프레임워크를 사용한 데이터 저장
  - Map<Long, Product> products