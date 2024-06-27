# spring-gift-product

## 🚀 상품 API <hr>
### 기능 요구사항
- HTTP API 구현
  - [X] 상품 조회
  - [X] 상품 추가
  - [X] 상품 수정
  - [X] 상품 삭제
  > JSON 형식으로 request-response한다.
  적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장

<br>

## 🚀 관리자 화면  <hr>
### 기능 요구사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면 구현
  - [X] 상품 조회
  - [X] 상품 추가
  - [X] 상품 수정
  - [X] 상품 삭제
  > Thymeleaf를 사용하여 서버 사이드 렌더링을 구현
  > 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.


## 🚀 데이터베이스 적용  <hr>
### 기능 요구사항
- 자바 컬렉션 프레임워크로 메모리에 저장하던 상품 정보를 **데이터베이스**에 **저장**   
  → H2 DB 사용   
  → 애플리케이션이 실행될 때 사용하는 테이블 구축되어야 한다.