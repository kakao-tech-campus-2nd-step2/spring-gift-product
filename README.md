# spring-gift-product

## step1

### 기능
- 상품 조회(GET)
- 상품 추가(POST)
- 상품 수정(PUT)
- 상품 삭제(DELETE)

### 할일
1. Product 클래스 정의
> product의 id, name, price, imageUrl 정의
2. ProductController 클래스 정의
> HTTP 요청 처리, JSON 응답 반환
> Map 사용하여 상품 데이터 저장
> 
## step2

### 기능
- 관리자 화면을 통한 상품 조회, 추가, 수정, 삭제

### 할일
1. 관리자 화면 구현
    - 상품 목록 페이지
    - 상품 추가 페이지
    - 상품 수정 페이지
2. ProductAdminController 클래스 정의
    - 관리자 화면 요청 처리
3. Thymeleaf를 사용한 서버 사이드 렌더링 구현
    - HTML 템플릿 작성
4. HTML 폼 전송을 통한 페이지 이동 구현
5. 상품 이미지 URL을 통한 이미지 표시 기능 구현

### 관리자 화면 상세 설명
- **상품 목록 페이지**
    - URL: `/admin/products`
    - 설명: 모든 상품을 테이블 형식으로 조회, 수정 및 삭제 링크 제공
- **상품 추가 페이지**
    - URL: `/admin/products/add`
    - 설명: 새로운 상품을 추가할 수 있는 폼 제공
- **상품 수정 페이지**
    - URL: `/admin/products/edit/{id}`
    - 설명: 기존 상품을 수정할 수 있는 폼 제공

## step3

### 기능
- H2 데이터베이스 적용
- 데이터베이스 초기화 스크립트 작성
- 상품 정보 데이터베이스에 저장

### 할일
1. 의존성 추가
    - H2 데이터베이스
    - Spring JDBC
2. 데이터베이스 설정
    - application.properties 파일 설정
3. 데이터베이스 초기화 스크립트 작성
    - schema.sql
    - data.sql
4. ProductRepository 클래스 작성
    - JDBC를 사용하여 데이터베이스와 상호작용
5. ProductController 클래스 수정
    - 메모리 저장 로직 제거
    - 데이터베이스와 상호작용하도록 변경