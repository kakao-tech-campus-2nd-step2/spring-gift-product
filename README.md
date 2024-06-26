# spring-gift-product

## 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
HTTP 요청과 응답은 JSON 형식으로 주고받는다.
현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

## 클래스 설명

### controller
client 요청에 대한 적절한 logic을 수행함. 

### Product
상품을 전달하는 DTO 역할을 함. record 객체로 불변성 보장.

