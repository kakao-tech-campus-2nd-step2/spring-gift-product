# spring-gift-product

### 🚀 1단계 - 상품 API
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API 구현
- HTTP 요청과 응답은 JSON 형식으로 주고받음
- 현재는 별도의 데이터베이스가 없으므로 적절할 자바 컬렉션 프레임워크를 사용하여 메모리에 저장

1. 상품 조회 기능
    - 상품을 조회할 수 있는 API 구현
    - HTTP Method: GET
    - URL: /api/products
      <br><br>
2. 상품 추가 기능
    - 새로운 상품을 추가할 수 있는 API 구현
    - HTTP Method: POST
    - URL: /api/products
      <br><br>
3. 상품 수정 기능
    - 상품을 수정할 수 있는 API 구현
    - HTTP Method: PUT
    - URL: /api/products/{id}
      <br><br>
4. 상품 삭제 기능
    - 상품을 삭제할 수 있는 API 구현
    - HTTP Method: DELETE
    - URL: /api/products/{id}

<br>hr>

### 🚀 2단계 - 관리자 화면
1. 상품 조회 기능
   - 상품 목록을 테이블 형태로 표시
   - 각 상품의 정보가 함께 보임
   <br><br>
2. 상품 추가 기능
   - 상품 추가 페이지
   - 상품명(name), 가격(price), imageUrl 입력 폼
   - 폼 제출 버튼 클릭 시 서버에 상품 추가 요청
   <br><br>
3. 상품 수정 기능
   - 상품 조회 페이지에서 각 상품을 수정할 수 있는 버튼 필요
   - 상품 수정 페이지
   - 상품명(name), 가격(price), imageUrl 수정 폼
   - 수정 성공 시, 상품 조회 페이지로 이동
   <br><br>
4. 상품 삭제 기능
   - 상품 조회 페이지에서 각 상품을 삭제할 수 있는 버튼 필요
   - 삭제 버튼 클릭 시, 서버에 해당 상품 삭제 요청
   - 삭제 성공 시, 상품 조회 페이지 갱신