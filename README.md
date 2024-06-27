# spring-gift-product

# 과제 진행 요구사항

- 기능 구현 전 READ.md에 구현할 기능 목록 정리해 추가
- Git의 커밋 단위는 README.md에 정리한 기능 목록 단위로 추가
- Angular JS Git Commit Message Convention 참고해 커밋 메시지 추가

# 기능 요구 사항

### Step1

상품을 조회,추가,수정,삭제할 수 있는 간단한 HTTP API 구현

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 적절한 자바 컬렉션 프레임워크를 사용해 메모리에 저장


### Step2

상품을 조회,추가,수정,삭제 할 수 있는 관리자 화면 구현.

- Thymeleaf를 사용해 SSR 구현.
- 이미 만든 상품 API를 이용해 fetch를 이용한 비동기 통신을 이용해 구현

# 구현할 기능

### Step1

- 필요한 속성을 가지고 있는 상품 객체(Product)생성.
- 상품목록을 가지고 있는 객체(Products) 생성.
- HTTP 요청을 수행할 Controller 생성.
  - GET, POST, DELETE, PATCH 요청에 따른 동작 수행.


### Step2

- View 파일을 resources/templates 경로에 만듬.
- View를 뿌려줄 새로운 Controller 생성.
- 컨트롤러를 2개로 분리했기 때문에 Products 객체를 싱글톤으로 리팩토링.
- JS를 이용해 비동기 통신 구현.
