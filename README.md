# spring-gift-product
### Step 1. 상품 API
- GET 요청을 통한 **정보 조회 기능** 구현
- 필요한 사항
  1. 상품의 정보를 담을 DTO 클래스
  2. 상품들을 저장할 HashMap
  3. 위의 정보를 Json 형식으로 반환해줄 Controller 클래스
- 비고
  - 현재는 별도의 데이터베이스가 없으므로 적절한 컬렉션을 이용하여 메모리에 저장한다.
- **추가**
  - CRUD 모두를 구현해야 한다!!!
### Step 2. 관리자 페이지
- CRUD 기능을 수행하는 **관리자 페이지** 구현
- 필요한 사항
  1. Read를 제외한 Create, Update, Delete API 구현
  2. HTTP 요청을 보낼 HTML Form 작성
     - Form은 Create, Upadate에 대해서만 있으면 된다
  3. 이전에 만든 객체 전송 사양을 View로 바꾸어야 한다
     - 기존 : @RestController를 이용하여 객체를 반환
     - 신규 : @Controller를 이용하여 View에 정보를 전달하고 이를 반환