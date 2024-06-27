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
### Step 3. 데이터베이스 적용
- 기존에 메모리에 저장되는 HashMap에 저장하던 방식을 넘어, 데이터베이스에 정보를 저장
- 필요한 사항
  1. 서버 구동 시, 데이터베이스 초기화 및 테이블 구축
  2. 데이터베이스는 h2를 사용
  3. Create -> INSERT / Update -> UPDATE 등, 매칭되는 DB 쿼리문을 사용