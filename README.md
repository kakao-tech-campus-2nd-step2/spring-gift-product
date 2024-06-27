# 구현 기능 목록 명세

---

# Step 1

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
    - DeleteItem : 상품 삭제 (/api/product/{id}) (DELETE)~~~~

# Step 2
## 추가된 기능
1. 관리자 페이지(View 구현)
2. Controller와 Service 분리
3. ItemDTO 구현
4. Controller와 Service unit Test 추가

## 구현 목록
### Controller
- getList : 상품 목록 페이지 요청 처리 (GET-"/product/list")
- getCreateForm : 상품 추가 페이지 요청 처리 (GET-"/product/create")
- createItem : 상품 추가 요청 처리 (POST-"/product/create")
- getUpdateForm : 상품 수정 페이지 요청 처리 (GET-"/product/update/{id}")
- updateItem : 상품 수정 요청 처리 (PUT-"/product/update/{id}")
- deleteItem : 상품 삭제 요청 처리 (DELETE-"/product/delete/{id}")

### Service
- insertItem : 상품 추가 로직
- findItem : id로 상품 조회
- getList : 상품 목록 반환
- updateItem : 상품 수정
- deleteItem : 상품 삭제

### View
- list : 상품 목록 페이지, 메인 페이지, 상품 추가, 수정, 삭제 버튼 존재
- create : 상품 추가 페이지
- update : 상품 수정 페이지
