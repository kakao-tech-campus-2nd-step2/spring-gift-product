# spring-gift-product

# 프로젝트 요구사항

## 기능 요구사항

- 상품을 조회, 추가, 수정, 삭제 (CRUD) 할 수 있는 간단한 HTTP API를 구현한다
  - 현재는 별도의 DB가 없어서 적절한 JCF를 사용하여 메모리에 저장한다.
  - HTTP Request와 response는 JSON 형식으로 주고받는다.
  - 아래의 예시와 같이 구현한다.

- REQUSET
```GET /api/products HTTP/1.1```
- Response
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

# 프로젝트 구조
- src/main/java
  - controller
    - ProductController.java
  - domain
    - ProductDTO.java
  - service
    - ProductService.java
  - repository
    - ProductDAO.java
  - Application.java

# 상품 도메인 정의

- 상품의 상태 정보
  - Long타입의 id
  - String타입의 name
  - Long타입이고 한화(원)단위의 price
  - String타입의 이미지 URL


# 프로젝트 기능

- 전체 상품 조회
- 단일 상품 조회
- 상품 추가
- 상품 삭제
- 상품 수정

# 해당 API

1. 전체 상품 조회
- HTTP method: GET
- PATH: /api/products
- parameter: x

- Request
  ```GET /api/products HTTP/1.1```
- Response
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    [
      {
        "id": 1,
        "name": "아이스 아메리카노",
        "price": 5500,
        "imageUrl": "http://image1.png"
      }
    ]
    ....나머지 product들에 대한 추가 JSON
    ```
2. 단일 상품 조회
- HTTP method: GET
- PATH: /api/products/{productId}
- parameter: id
- Request
  ```GET /api/products/{productId} HTTP/1.1```
- Response
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    [
      {
        "id": 1,
        "name": "아이스 아메리카노",
        "price": 5500,
        "imageUrl": "http://image1.png"
      }
    ]
    ```

3. 상품 추가
- HTTP method: POST
- PATH: /api/products
- parameter : x
- Request
  ```POST /api/products HTTP/1.1```
- Response
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    [
      {
        "id": 2,
        "name": "레모네이드",
        "price": 4500,
        "imageUrl": "http://image2.png"
      }
    ]
    ```
4. 상품 삭제
- HTTP method: DELETE
- PATH: /api/products/{productId}
- parameter : id
- Request
  ```DELETE /api/products/{productId} HTTP/1.1```
- Response
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    [
      {
        "id": 1
      }
    ]
    ```
5. 상품 수정
- HTTP method: PUT
- PATH: /api/products/{productId}
- parameter : id
- Request
  ```PUT /api/products/{productId} HTTP/1.1```
- Response
    ```
    HTTP/1.1 200 
    Content-Type: application/json
    
    [
      {
        "id": 1
        "name": "콜라",
        "price": 2500,
        "imageUrl": "http://image1.png"
      }
    ]
    ```
