# spring-gift-product

## 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

- HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

## 기능 목록

> ✔️ **일러두기**
> - [ ] : 미구현한 기능
> - [x] : 구현한 기능
> - [ ] <기능> (test: -/yes) : <기능>에 대한 테스트케이스 작성 여부

### 모델 설계

- [ ] 상품을 표현하는 객체를 생성하기
  - 상품의 구성요소
    - id: int
    - name: string
    - price: int
    - imageUrl: string
- [ ] 상품을 저장하는 데이터베이스 표현하기
  - 해시맵으로 표현하기

### 기능 설계(컨트롤러 설계)

- [ ] 상품을 추가하는 API 구현하기
- [ ] 상품 리스트를 조회하는 API 구현하기
- [ ] 상품을 수정하는 API 구현하기
- [ ] 상품을 삭제하는 API 구현하기

### 기타 구현사항

- [ ] 상품을 관리하는 컨트롤러 생성하기
- [ ] 데이터베이스 관리 레포지토리 생성하기
- [ ] 레포지토리를 이용해 특정 서비스를 제공해주는 서비스 생성하기

---



## API 명세서

### 상품 리스트 조회 API

- Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| GET    | /api/products | -          | -             | -    |


- Response

| Status | Body |
|--------|------|
| 200 OK | Yes  |

- Response/Body 

```json
[
  {
    "id": 8146027,
    "name": "아이스 카페 아메리카노 T",
    "price": 4500,
    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
  },
  {}, ...
]
```

---

### 상품 추가 API

- Request

| Method | URL           | Path param | Path variable | Body |
|--------|---------------|------------|---------------|------|
| POST   | /api/products | -          | -             | Yes  |

- Request/Body

```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

- Response(success)

| Status      | Body |
|-------------|------|
| 201 Created | Yes  |

- Response(success)/Body
  - 생성된 리소스의 id를 응답함

```json
{
  "id": 23
}
```

- Response(fail)
  - 기등록된 상품 중 name, price, ImageUrl이 모두 일치할 경우 발생

| Status       | Body |
|--------------|------|
| 409 Conflict | -    |

---

### 상품 수정 API

- Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| POST   | /api/products/{id} | -          | yes{id: int}  | yes  |

- Request/Body
```json
{
  "name": "Product name",
  "price": 10000,
  "ImageUrl": "http://~"
}
```

- Response(sucess)

| Status | Body |
|--------|------|
| 200 OK | -    |

- Response(fail)
  - 수정하려는 상품이 존재하지 않을 경우 발생


| Status        | Body |
|---------------|------|
| 404 NOT FOUND | -    |

---

### 상품 삭제 API

- Request

| Method | URL                | Path param | Path variable | Body |
|--------|--------------------|------------|---------------|------|
| DELETE | /api/products/{id} | -          | yes{id: int}  | -    |

- Response(sucess)

| Status | Body |
|--------|------|
| 200 OK | -    |

- Response(fail)
    - 삭제하려는 상품이 존재하지 않을 경우 발생

| Status        | Body |
|---------------|------|
| 404 NOT FOUND | -    |