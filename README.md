# spring-gift-product

# 1단계 - 상품 API

# 프로그래밍 요구 사항
## 자바 코드 컨벤션을 지키면서 프로그래밍한다.
* 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)를 원칙으로 한다.
* 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.

## indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 한다. 2까지만 허용한다.
* 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
* 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.

## 3항 연산자를 쓰지 않는다.

## 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
* 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.

## else 예약어를 쓰지 않는다.
* else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
* 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.

# 기능 요구 사항
## 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
* HTTP 요청과 응답은 JSON 형식으로 주고받는다.
* 현재는 별도의 데이터베이스가 없으므로 적적한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

# 기능
* 상품 조회: 모든 상품 목록을 조회합니다.
* 상품 추가: 새로운 상품을 추가합니다.
* 상품 수정: 상품 ID를 통해 기존의 상품 정보를 수정합니다.
* 상품 삭제: 상품 ID를 통해 기존의 상품 정보를 삭제합니다.

# API 명세서
## 1. 모든 상품 조회
* URL: `api/products`
* Method: `GET`
* Request: 없음
* Response:
    - Status: `200 OK`
    - Body:
        ```json
        [
          {
            "id": 8146027,
            "name": "아이스 카페 아메리카노 T",
            "price": 4500,
            "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
        ]
        ```

## 2. 특정 상품 조회
* URL: `api/products/{id}`
* Method: `GET`
* Request: 없음
* Response:
    - Status: `200 OK`
    - Body:
        ```json
        {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
        ```

## 3. 상품 추가
* URL: `api/products`
* Method: `POST`
* Request:
    - Body:
        ```json
        {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
        ```
* Response:
    - Status: `201 OK`
    - Body:
         ```json
        {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
        ```

## 4. 상품 수정
* URL: `api/products/{id}`
* Method: `PUT`
* Request:
    - Path Variable: 'id' (수정할 상품의 ID)
    - Body:
        ```json
        {
          "name": "아이스 카페 아메리카노 L",
          "price": 5000,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
        ```
* Reponse:
    - Body:
        ```json
        {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 L",
          "price": 5000,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        }
        ```
* Eroor Response:
    - Status: `404 Not Found`

## 5. 상품 삭제
* URL: `api/products/{id}`
* Method: `DELETE`
* Request:
    - Path Variable: 'id' (수정할 상품의 ID)
* Reponse:
    - Status: `204 No Content`
* Error Response:
    - Status: `404 Not Found`