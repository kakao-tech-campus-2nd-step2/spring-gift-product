# 구현 기능 목록 명세

---
### 기능 목록

1. 상품 추가
2. 상품 조회
    1. 단일 상품 조회
    2. 상품 목록 조회
3. 상품 수정
4. 상품 삭제

### 구현 목록-

- Item : 상품 객체
    - id, name, price ,imgUrl 속성을 가짐
- ItemController
    - CreateItem : 상품 추가 요청 처리  (/api/product) (POST)
    - FindItem : 상품 조회 요청 처리 (/api/product/{id}) (GET)
    - FindItemList : 상품 목록 조회 요청 처리  (/api/product/list) (GET)
    - UpdateItem : 상품 수정 (/api/product/{id}) (PUT)
    - DeleteItem : 상품 삭제 (/api/product/{id}) (DELETE)