# spring-gift-product

## Step1 상품 API

- [x] Product 클래스 생성
- [x] Products 클래스 생성
  - [x] Bean등록
  - [x] DI
- [x] CRUD
  - [x] GetMapping
    - [x] Test
  - [x] PostMapping
    - [ ] Test
  - [x] PatchMapping
    - [ ] Test
  - [x] DeleteMapping
    - [x] Test


## Step3 데이터베이스 적용

### 요구사항
자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.
메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.


### 기능 구현 목록

- [ ] ProductRepository Interface를 사용하여 JDBCProductRepositoryTest 구현
- [ ] ProductRepository Interface를 구현한 JDBCTemplateProductRepository 구현