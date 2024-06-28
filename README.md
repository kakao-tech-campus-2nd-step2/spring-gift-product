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

## step3

자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 DB에 저장한다.

* 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경
* 사용하는 테이블은 에플리케이션이 실행될 때, 구축되어야 한다.
* JdbcTemplate, SimpleJdbcinsert 및 JdbcClient와 같은 도구를 사용할 수 있다
* 스키마 스크립트는 schema.sql, 데이터 스크립트는 data.sql, 각각의 위치는 spring.sql.init.schema-locations, spring.sql.init.data-lcations을 사용하여 사용자 지정 가능하다.
* 기본적으로 내장된 메모리 내 DB를 사용할 때만 수행된다.
  * 항상 SQL 데이터베이스를 초기화하려면 spring.sql.init.mode 를 always로 설정한다.
