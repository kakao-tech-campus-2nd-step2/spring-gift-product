# spring-gift-product

### 기본 URL
```
http://localhost:8080/api/products
```

### 엔드포인트

#### 1. 상품 생성
새로운 상품을 생성하고 저장함
- **URL:** `/`
- **Method:** `POST`
- **body(json):**
    ```json
    {
        "id": 1,
        "name": "Product Name",
        "price": 1000,
        "imageUrl": "http://example.com/image.jpg"
    }
    ```
- **응답:**
    - **Status:** `200 OK`
    - **Body:**
        ```json
        {
            "id": 1,
            "name": "Product Name",
            "price": 1000,
            "imageUrl": "http://example.com/image.jpg"
        }
        ```

#### 2. ID로 상품 조회
주어진 ID에 해당하는 상품을 조회함
- **URL:** `/{id}`
- **Method:** `GET`
- **URL 파라미터:**
    - `id` (필수): 조회할 상품의 ID.
- **응답:**
    - **Status:** `200 OK`
    - **Body:**
        ```json
        {
            "id": 1,
            "name": "Product Name",
            "price": 1000,
            "imageUrl": "http://example.com/image.jpg"
        }
        ```
    - **Status:** `404 Not Found`

#### 3. 모든 상품 조회
모든 상품을 조회함
- **URL:** `/all`
- **Method:** `GET`
- **응답:**
    - **Status:** `200 OK`
    - **Body:**
        ```json
        [
            {
                "id": 1,
                "name": "Product Name",
                "price": 1000,
                "imageUrl": "http://example.com/image.jpg"
            },
            {
                "id": 2,
                "name": "Another Product",
                "price": 2000,
                "imageUrl": "http://example.com/image2.jpg"
            }
        ]
        ```

#### 4. 상품 삭제
주어진 ID에 해당하는 상품을 삭제함
- **URL:** `/{id}`
- **Method:** `DELETE`
- **URL 파라미터:**
    - `id` (필수): 삭제할 상품의 ID.
- **응답:**
    - **Status:** `204 No Content`
    - **Status:** `404 Not Found`

#### 5. 상품 갱신
주어진 ID에 해당하는 상품을 갱신함
- **URL:** `/{id}`
- **Method:** `PUT`
- **body(json):**
    ```json
    {
        "id": 1,
        "name": "Updated Product Name",
        "price": 1500,
        "imageUrl": "http://example.com/updated-image.jpg"
    }
    ```
- **응답:**
    - **Status:** `200 OK`
    - **Body:**
        ```json
        {
            "id": 1,
            "name": "Updated Product Name",
            "price": 1500,
            "imageUrl": "http://example.com/updated-image.jpg"
        }
        ```
    - **Status:** `404 Not Found`

### 오류 처리
모든 오류 응답은 다음 구조를 따름:
```json
{
    "status": ",<HTTP_STATUS_CODE>",
    "error": "<ERROR>",
    "message": "<ERROR_MESSAGE>",
    "path": "<REQUEST_PATH>"
}
```

### 스키마

#### 상품 (Product)
- **속성:**
    - `id` (Long): 상품의 고유 식별자.
    - `name` (String): 상품 이름.
    - `price` (Long): 상품 가격.
    - `imageUrl` (String): 상품 이미지 URL.

### 사용 예시

#### 상품 생성
```sh
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"id":1,"name":"Product Name","price":1000,"imageUrl":"http://example.com/image.jpg"}'
```

#### ID로 상품 조회
```sh
curl -X GET http://localhost:8080/api/products/1
```

#### 모든 상품 조회
```sh
curl -X GET http://localhost:8080/api/products/all
```

#### 상품 삭제
```sh
curl -X DELETE http://localhost:8080/api/products/1
```

#### 상품 갱신
```sh
curl -X PUT http://localhost:8080/api/products/1 -H "Content-Type: application/json" -d '{"id":1,"name":"Updated Product Name","price":1500,"imageUrl":"http://example.com/updated-image.jpg"}'
```