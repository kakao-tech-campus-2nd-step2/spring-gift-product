# spring-gift-product

## 기능 목록

### Step 1

* 상품 전체 조회 API

* 상품 추가 API

* 상품 수정 API

* 상품 삭제 API

### Step 2

API 호출은 axios 라이브러리 사용

* 상품 단건 조회 API

* 상품 전체 조회 View

  * 상품 전체 조회 API 호출
  
* 상품 추가 View

  * 상품 추가 Form
  
  * 상품 추가 API 호출

* 상품 수정 View

  * 상품 수정 Form
  
    * 기존에 등록된 상품 정보를 가져오기 위한 상품 단건 조회 API 호출
    
  * 상품 수정(PUT) API 호출

* 상품 삭제 View
  
  * 상품 삭제 API 호출

## API 문서

### 상품 전체 조회 API

* request

  ```http
  GET /api/products HTTP/1.1
  ```

* response

  ```http
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

### 상품 추가 API

* request

  ```http
  POST /api/products HTTP/1.1
  Content-Type: application/json
  
  {
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }

  ```

* response

  ```http
  HTTP/1.1 201 
  Content-Type: application/json
  
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
  ```

### 상품 수정 API

* request

  ```http
  PUT /api/products/{productId} HTTP/1.1
  Content-Type: application/json

  {
    "name": "복숭아 아이스티",
    "price": 3500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
  ```

* response

  ```http
  HTTP/1.1 200 
  Content-Type: application/json
  
  {
    "id": 8146027,
    "name": "복숭아 아이스티",
    "price": 3500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  }
  ```

### 상품 삭제 API

* request

  ```http
  DELETE /api/products/{productId} HTTP/1.1
  ```

* response

  ```http
  HTTP/1.1 200 
  Content-Type: application/json
  
  {
    "id": 8146027
  }
  ```
