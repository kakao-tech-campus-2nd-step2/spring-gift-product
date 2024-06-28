# spring-gift-product

## step1 : 상품 API

### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
HTTP 요청과 응답은 JSON 형식으로 주고받는다.
현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

## 클래스 설명

### Productcontroller
ProductController : client 요청에 대한 적절한 logic을 수행함. 

### Product
상품을 전달하는 DTO 역할을 함. record 객체로 불변성 보장.

## step 2 : Admin page

### 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
아래 예시와 꼭 같을 필요는 없으며, 가볍게 참고한다.

## 클래스 및 HTML 설명

### AdminController
Admin Page를 위한 적절한 endpoint 설정 및 thymeleaf SSR 수행

### resources/templates 
- main.html : 관리자 메인 화면. endpoint : http://localhost:8080/admin
- get.html : 상품 조회 페이지. endpoint : http://localhost:8080/admin/get
- add.html : 상품 추가 페이지. endpoint : http://localhost:8080/admin/post
- delete.html : 상품 삭제 페이지. endpoint : http://localhost:8080/admin/delete
- update.html : 상품 업데이트 페이지. endpoint : http://localhost:8080/admin/put

상품의 id를 입력받은 후, 제출을 통해 ProductController의 데이터 접근.