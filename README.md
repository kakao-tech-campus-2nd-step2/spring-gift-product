# spring-gift-product

## <1단계 상품 API>

***

### 구현할 기능 목록

- 상품 리소스를 표현할 객체 만들기
    - record 클래스로 만들기
    - id, name, price, imageUrl 필드 만들기
- 컨트롤러 만들기
    - 상품 데이터 관리를 위해 메모리에 저장하기 위한 컬렉션 생성
    - 상품을 추가할 수 있는 HTTP API 구현
        - PostMapping 사용
        - /api/products?
        - 컬렉션을 이용하여 메모리에 저장
    - 단일 상품의 정보를 조회할 수 있는 HTTP API 구현
        - GetMapping 사용
        - /api/products/{id}
            - id에 해당하는 상품만 출력하기
    - 모든 상품의 정보를 조회할 수 있는 HTTP API 구현
        - GetMapping 사용
        - /api/products/all
        - 현재 메모리에 저장되어 있는 모든 상품들을 출력
    - 상품의 정보를 수정할 수 있는 HTTP API 구현
        - PutMapping 사용
    - 상품의 정보를 삭제할 수 있는 HTTP API 구현
        - DeleteMapping 사용

### 프로그래밍 요구 사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 Google Java Style Guide를 원칙으로 한다.
    - 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- else 예약어를 쓰지 않는다.
    - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.

***

## <2단계 관리자 화면>

***

### 구현할 기능 목록

- 상품들을 조회할 수 있는 HTML 틀 작성
    - 상품 조회 창에서 이미지 Url을 누르면 해당 url로 이동
- 상품을 추가할 수 있는 HTML 틀 작성
- 상품을 수정할 수 있는 HTML 틀 작성
- 상품 조회 창에서 '상품 추가'를 누르면 추가할 수 있는 HTMl로 이동
    - 값을 넣은 후 저장
    - 저장된 후 상품 조회 창으로 이동
- 상품 조회 창에서 '수정'을 누르면 수정할 수 있는 HTML로 이동
    - 값을 변경 후 저장을 누르면 그 값으로 저장
    - 저장된 후 상품 조회 창으로 이동
- 상품 조회 창에서 '삭제'를 누르면 제거되게 설정
- 새로운 컨트롤러 생성
    - 상품 목록 조회 페이지 표시 메서드
        - GetMapping 사용
        - Url은 /admin/products
        - 현재 메모리에 등록된 모든 상품들을 조회하여 표시
    - 상품 추가 페이지 표시 메서드
        - GetMapping
        - Url은 /admin/products/add
        - 상품을 추가할 수 있는 HTML 보여주기
    - 상품 추가 메서드
        - PostMapping
        - Url은 /admin/products/add
        - 상품을 추가
        - id가 이미 존재하면 에러 메시지 표시하고 다시 html 표시
        - 성공적으로 추가되면 상품 목록 조회 페이지로 이동
    - 상품 수정 페이지 표시 메서드
        - GetMapping
        - Url은 /admin/products/edit/{id}
        - id에 해당하는 상품 수정 HTML 표시
    - 상품 수정 메서드
        - PostMapping
        - Url은 /admin/products/edit/{id}
        - 전송된 데이터를 받아 특정 id의 제품 표시
        - 수정하려는 id가 이미 존재하면 에러 메시지 표시하고 다시 html 표시
        - 성공적으로 수정되면 상품 목록 조회 페이지로 이동
    - 상품 삭제 메서드
        - DeleteMapping
        - /admin/products/delete/{id}
        - 특정 id의 제품을 삭제
        - 성공적으로 삭제되면 상품 목록 조회 페이지로 이동

***

## <3단계 데이터베이스 적용>

***

### 구현할 기능 목록

- 데이터베이스 설정
    - application.properties 파일에 데이터베이스 설정 추가
- 데이터베이스 초기화
    - sql 스크립트를 활용해서 초기화
- ProductRepository 생성
    - 모든 상품 조회
    - 주어진 id에 해당하는 상품 조회
    - 상품 저장
    - 상품 삭제
    - 상품 업데이트
- AdminController 수정
    - 메모리에 저장하고 있던 모든 코드 제거
    - ProductRepository를 사용하도록 코드 수정