# spring-gift-product

## 1단계 기능 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

HTTP 요청과 응답은 JSON 형식으로 주고받는다.
현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

1. 상품 등록 기능
    * 상품은 이름,가격,이미지 사진에 대한 정보를 가진다.
    * 상품을 구분하기 위해 각 상품은 id값을 가지고 이 값은 반드시 고유한 값이어야한다.
    * 가격은 1원 ~ 1,000,000,000원의 범위를 가진다. 그 외의 범위는 가질 수 없다.
2. 상품 조회 기능
   * 상품은 id로 조회할 수 있고, 조회 시 상품의 이름, 가격, 이미지에 대한 정보를 반환한다.
   * 존재하지 않는 id를 조회하는 경우 에러메시지를 반환한다.
3. 상품 수정 기능
   * 요청하는 정보로 상품 정보를 수정한다.
   * 존재하지 않는 id에 대해서 수정 요청 보내는 경우 에러메시지를 반환한다.
4. 상품 삭제 기능
   * id를 요청하여 해당 id를 가진 상품을 데이터베이스에서 삭제한다.
   * 성공적으로 삭제가 되었다면 ```204``` 응답코드를 반환한다.
   * 존재하지 않는 id에 대해서 상품 삭제 요청을 보내는 경우 에러메시지를 반환한다.

## 2단계 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.

----
### 기능 요구 사항
* Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
* 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
* 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

1. 상품 조회 화면
   * 상품 조회 후 삭제할 수 있다
2. 상품 등록 화면
3. 상품 수정 화면

## 3단계 요구 사항

### 기능 요구 사항
자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

### 프로그래밍 요구 사항
메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

