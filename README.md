# spring-gift-product
### Step 1. 상품 API
- GET 요청을 통한 **정보 조회 기능** 구현
- 필요한 사항
  1. 상품의 정보를 담을 DTO 클래스
  2. 상품들을 저장할 HashMap
  3. 위의 정보를 Json 형식으로 반환해줄 Controller 클래스
- 비고
  - 현재는 별도의 데이터베이스가 없으므로 적절한 컬렉션을 이용하여 메모리에 저장한다.