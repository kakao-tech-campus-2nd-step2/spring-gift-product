# spring-gift-product
___

## Step1 - 상품 API
___

### 기능 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

### 구현할 기능 목록

- [x] : ProductController
  - 요청에 대한 응답 반환
    - `record`를 사용해 응답 형식이 저장된 Product의 구조에 의존하지 않도록 함
  - Collection Framework의 `Map`을 사용해 메모리에 상품 저장
  - 상품의 조회, 추가, 수정, 삭제 엔드포인트를 각각 구현

- [x] : Product
  - 상품 정보의 저장 형태
  - id, name, price, imageUrl 을 갖는다

## Step2 - 관리자 화면
___

### 기능 요구사항
상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
- Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

### 구현할 기능 목록

- [x] : AdminController
  - 상품 목록 조회, 추가, 수정 페이지로 이동하는 엔드포인트 구현
    - thylemeaf 사용
    - `@GetMapping("/admin/products")`: 상품 목록 조회
    - `@GetMapping("/admin/products/new")`: 상품 추가 페이지로 이동
    - `@GetMapping("/admin/edit-product?product-id={product-id}")`: 상품 수정 페이지로 이동

  - 상품 목록 조회, 추가, 수정, 삭제는 기존의 ProductController를 이용
    - `@GetMapping("/products")`: 상품 목록 조회
    - `@PostMapping("/products")`: 상품 추가
    - `@PutMapping("/products/{product-id}")`: 상품 수정
    - `@DeleteMapping("/products/{product-id}")`: 상품 삭제

- [x] : javascript 사용
  - 비동기 방식으로 상품 목록 조회, 추가, 수정, 삭제
  - `Fetch API`를 사용해 서버와 통신

- [x] : CSS 사용
  - `Bootstrap`을 사용해 디자인
