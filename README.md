# spring-gift-product
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
***
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