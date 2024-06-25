# spring-gift-product
기능 요구 사항: 상품을 조회, 수정, 삭제 할 수 있는 간단한 HTTP API를 구현한다.

1. HTTP 요청과 응답은 JSON 형태로 주고받는다
2. 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용해서 메모리에 저장

# 1단계 - 상품 API

1. id, name, price, imageURL 의 4개의 정보를 가진 모델 생성
2. GET /api/products 로 보냈을때 응답 생성하도록 구현 