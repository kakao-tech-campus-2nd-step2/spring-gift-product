# spring-gift-product

구현할 기능 목록

## step1

상품 정보 DTO - record로 구현 (gift.web.dto.Product)

HTTP API - ProductController (gift.web.ProductController)

- POST
- GET
- PUT / PATCH
- DELETE

## step2

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

Thymeleaf를 사용해, HTML 폼 전송을 이용한 페이지 이동을 기반으로 구현

* ProductController로 view를 반환하도록 재작성
* index(product) html, create update html 작성
