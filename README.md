# spring-gift-product

**🚀 1단계 - 상품 API**
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