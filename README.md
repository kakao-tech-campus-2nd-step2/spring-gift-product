# spring-gift-product

## 1단계 - 상품 API
### 미션 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
* HTTP 요청과 응답은 JSON 형식으로 주고받는다
* 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다

### 예시
#### Request
```
GET /api/products HTTP/1.1
```
#### Response
```
HTTP/1.1 200 
Content-Type: application/json

[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
]
```
### 구현할 기능 목록
* Spring MVC 구조
* 전체 상품 조회 API
* ID로 상품 조회 API
* 상품 추가 API
* 상품 수정 API
* 상품 삭제 API

---

## 2단계 - 관리자 화면
### 미션 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
* Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다
* 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX] 등의 방식을 적용할 수 있다
* 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다

### 구현할 기능 목록
* 상품 목록(Thymeleaf) 페이지
* 상품 수정(fetch) 함수
* 상품 삭제(fetch) 함수
* 상품 추가(fetch) 함수
---

## 3단계 - 데이터베이스 적용

### 미션 요구 사항
자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다
* 메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
* 사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

### 구현할 기능 목록
* H2 데이터베이스 설정
* 데이터베이스 스키마 및 SQL 퀴리 구성
* 데이터베이스 적용
