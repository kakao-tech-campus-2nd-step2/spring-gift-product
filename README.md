# spring-gift-product

## 1단계
### HTTP 메서드 기반 간단한 상품 등록/조회/수정/삭제 API 구현
- 상품 조회(GET): 조회하고자하는 상품의 상품명을 쿼리 파라미터로 넘겨 조회 요청, 반환 값으로 상품 데이터를 받음
- 상품 등록(POST): http 요청 메시지 바디에 생성하고자하는 상품을 json 타입으로 작성하여 등록 요청
- 상품 수정(PUT): http 요청 메시지 바디에 수정하고자하는 상품명을 json 타입으로 작성하여 수정 요청
- 상품 삭제(DELETE): 삭제하고자하는 상품의 id을 쿼리 파라미터로 넘겨 삭제 요청

## 2단계
### 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면 구현
#### Controller
- getAllProducts(GET): 존재하는 모든 상품의 목록을 담아 뷰 반환
- addForm(GET): 상품 추가 뷰 반환
- addProduct(POST): 상품 추가 요청 처리
- editForm(GET): 상품 수정 뷰 반환
- updateProduct(POST): 상품 수정 요청 처리
- deleteProduct(GET): 상품 삭제 요청 처리
#### View
- products.html: 존재하는 모든 상품의 목록을 보여주는 화면
- addForm.html: 추가할 상품의 정보를 입력하는 화면
- editForm.html: 수정할 상품의 변경 정보를 입력하는 화면  