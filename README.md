# spring-gift-product
- 상품을 조회, 추가 수정, 삭제할 수 있는 간단한 HTTP API
- HTTP 요청, 응답은 JSON 형식으로 주고받는다.
- 별도 DB 없이 적절한 자바 컬렉션 프레임워크를 사용하요 저장한다.
- request : GET /api/products HTTP/1.1
- reponse : JSON 형식의 응답

## 기능
-[ ] 상품의 정보를 저장하는 product
-[ ] /api/products HTTP GET 요청을 수신하는 엔드포인트 controller
-[ ] product를 생성하는 메서드
