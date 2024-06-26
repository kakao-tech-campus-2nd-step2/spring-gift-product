# spring-gift-product

## 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

HTTP 요청과 응답은 JSON 형식으로 주고받는다. 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

## 시나리오

상품을 먼저 생성(추가)하는 기능 만든 후 조회, 수정, 삭제의 순서로 개발을 한다. DB는 따로 사용하지 않고 자바 컬렉션 프레임 워크를 사용한다.

1. 상품을 먼저 구현한다.(레코드로 구현)
    1. 상품은 id(Long), name(String), price(int), imageUrl(String)을 가진다.
    2. Map<id, product>와 product의 id는 다른것 같다.
    3. Map의 ID는 OrderID라고 생각한다. 
1. 상품을 CRUD HTTP API 구현
    1. 상품을 CRUD할 수 있는 컨트롤러를 구현한다.
    2. @RequestBody, @RequestParam 통해 JSON 형식으로 통신이 되도록 한다.

