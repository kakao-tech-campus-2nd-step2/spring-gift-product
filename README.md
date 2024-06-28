# spring-gift-product
# 1단계 기능 요구사항
---
## Resource Representation Class
### Product
- long id
- String name
- long price
- String imageUrl

## Resource Controller
### PdController
- Map<Long, Product> producsts
- public 조회
- public 추가
- public 수정
- public 삭제

## SpringApplication
### Application
- run();

# 2단계 기능 요구사항
---
## Controller
- 조회 -> products.html
- 추가 -> productsAdd.html -redirection -> products.html
- 수정 -> productsMod.html -redirection -> products.html
- 삭제 -redirection-> products.html
## View
- 상품목록 조회 html
  - 추가 버튼 -> 상품 추가 html로 이동
  - 체크박스 -> 삭제 버튼
  - 수정 버튼 -> 상품 수정 html로 이동
- 상품 추가 html
  - 데이터 입력 받은 후 Controller에 json 형식으로 제공
- 상품 수정 html
  - 데이터 입력 받은 후 Controller에 json 형시긍로 제공

 # 3단계 기능 요구사항
 ---
 ## ProductRepository
 - JdbcTemplate
 - createTable()
 - findAll()
 - findById()
 - save()
 - update()
 - deleteById()
## DatabaseInitializer
 - productRepository
 - run(String... args)
