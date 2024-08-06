# spring-gift-product

## 과제 진행 요구 사항


- 미션은 선물하기 상품 관리 저장소를 포크하고 클론하는 것으로 시작한다.

- 온라인 코드 리뷰 요청 1단계 문서를 참고하여 실습 환경을 구축한다.

  1. 미션 시작 버튼을 클릭하여 미션을 시작한다.
  2. 저장소에 GitHub 사용자 이름으로 브랜치가 생성되었는지 확인한다.
  3. 저장소를 내 계정으로 포크한다.

- 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.

- Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.

  - AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.

    

## 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.

- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

- 상품의 정보를 저장할 Product 클래스를 구현한다.

  - 상품의 ID, 이름, 가격, 이미지 URL을 포함하는 필드를 가진다.

- 상품의 조회, 추가, 수정, 삭제 요청을 처리하고 결과를 반환할 ProductController 클래스를 구현한다.

  - `GET '/api/products'` : 모든 상품 정보를 반환

  - `POST '/api/products'`: 새로운 상품 정보를 추가, ID가 이미 존재하는 경우 409 상태 코드를 반환

  - `PUT /api/products/{id}`: 주어진 ID에 해당하는 상품 정보를 수정, 해당 ID의 상품이 없을 경우 404 상태 코드를 반환

  - `DELETE /api/products/{id}`: 주어진 ID에 해당하는 상품 정보를 삭제, 해당 ID의 상품이 없을 경우 404 상태 코드를 반환

    ### 

#### Request 예시

```
GET /api/products HTTP/1.1
```



#### Response 예시

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





## 프로그래밍 요구 사항

- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
  - 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)를 원칙으로 한다.
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