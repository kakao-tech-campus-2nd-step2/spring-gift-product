# spring-gift-product

이 프로젝트는 선물 상품을 관리하기 위한 간단한 REST API입니다. 상품을 생성(Create), 조회(Read), 수정(Update), 삭제(Delete)할 수 있습니다. API는 HTTP 요청에 JSON 형식의 데이터로 응답합니다.

## 기능

- **상품 조회**: 모든 상품 목록을 조회합니다.
- **단일 상품 조회**: 상품 ID로 단일 상품의 상세 정보를 조회합니다.
- **상품 추가**: 새로운 상품을 추가합니다.
- **상품 수정**: 상품 ID로 기존 상품의 정보를 수정합니다.
- **상품 삭제**: 상품 ID로 상품을 삭제합니다.

## 엔드포인트

### 1. 모든 상품 조회

- **URL**: `/api/products`
- **Method**: `GET`
- **응답 예시**:
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

### 2. 단일 상품 조회

- **URL**: `/api/products/{id}`
- **Method**: `GET`
- **응답 예시**:
    ```json
    {
        "id": 8146027,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    }
    ```

### 3. 새로운 상품 추가

- **URL**: `/api/products`
- **Method**: `POST`
- **요청 본문**:
    ```json
    {
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    }
    ```
- **응답 예시**:
    ```json
    {
        "id": 8146027,
        "name": "아이스 카페 아메리카노 T",
        "price": 4500,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    }
    ```

### 4. 기존 상품 수정

- **URL**: `/api/products/{id}`
- **Method**: `PUT`
- **요청 본문**:
    ```json
    {
        "name": "아이스 카페 아메리카노 T",
        "price": 5000,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    }
    ```
- **응답 예시**:
    ```json
    {
        "id": 8146027,
        "name": "아이스 카페 아메리카노 T",
        "price": 5000,
        "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
    }
    ```

### 5. 상품 삭제

- **URL**: `/api/products/{id}`
- **Method**: `DELETE`
- **응답**: `204 No Content`

## 관리자 화면

이 프로젝트에는 제품 관리를 위한 간단한 관리자 화면도 포함되어 있습니다. 관리자는 웹 브라우저를 통해 제품 목록을 조회할 수 있습니다.

### 엔드포인트

- **URL**: `/admin/products`
- **Method**: `GET`
- **설명**: 이 엔드포인트는 관리자가 모든 제품을 조회할 수 있는 웹 페이지를 렌더링합니다.

### 관리자 화면 설정

- **AdminController.java**: 관리자 화면을 처리하는 컨트롤러입니다.
- **products_admin.html**: Thymeleaf 템플릿 파일로, 관리자 화면의 제품 목록을 렌더링합니다.