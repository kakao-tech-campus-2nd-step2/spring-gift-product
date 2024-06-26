# spring-gift-product
___

## Step1 - 상품 API
___

### 기능 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### 구현할 기능 목록

- [ ] : ProductController
  - 요청에 대한 응답 반환
    - `record`를 사용해 응답 형식이 저장된 Product의 구조에 의존하지 않도록 함
  - Collection Framework의 `Map`을 사용해 메모리에 상품 저장
  - 상품의 조회, 추가, 수정, 삭제 엔드포인트를 각각 구현

- [ ] : Product
  - 상품 정보의 저장 형태
  - id, name, price, imageUrl 을 갖는다
