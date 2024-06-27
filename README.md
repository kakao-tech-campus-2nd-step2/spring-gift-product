# spring-gift-product

## 구현할 기능 탐색
### POST : /products
1. 요청을 받는다
2. body에 담겨 있는 상품 정보 "리스트"를 순회하며 다음과 같은 작업을 한다
    1. id를 새로 생성한다
    2. 생성한 id가 이미 존재하면 1을 다시 수행한다.
    3. 상품 정보 목록에 추가한다.
3. 성공여부를 응답한다.
### GET : /products
1. 요청을 받는다.
2. 상품 정보 목록을 불러온다.
3. 해당 목록을 응답한다.
### DELETE : /products/{id}
1. 요청을 받는다.
2. id에 해당하는 상품이 있는지 확인한다.
3. 없으면 404 Not found를 응답
4. 있다면 레코드 삭제 후 `204 No content` 응답
### PUT : /products/{id}
1. 요청을 받는다.
2. id에 해당하는 상품이 있는지 확인한다.
3. 없으면 해당 id를 가지는 레코드를 추가하고 `201 Created` 응답.
4. 있다면 해당 레코드를 수정한 후 `200 Ok`와 함께 수정 후의 레코드 body에 담아 응답
### PATCH : /products/{id}
1. 요청을 받는다.
2. id에 해당하는 상품이 있는지 확인한다.
3. 없으면 404 not found를 응답
4. 있다면 해당 레코드 모든 필드에 대해 다음과 같은 작업을 수행한 후 `200 Ok`와 함께, 수정 후의 레코드 body에 담아 응답
   - 전달받은 콘텐츠의 필드값이 Null이 아니면, 해당 값으로 업데이트

## 클래스 설계
- 통신을 통한 요청과 응답만 있을 뿐 사용자에게 어떻게 보여줄지는 생각하지 않아도 되므로, 뷰는 존재하지 않아도 된다.
- 통신을 담당하는 컨트롤러가 존재해야 하며, 컨트롤러는 데이터를 JSON 형태로 변환해 반환할 수 있어야 하므로 `@RestController` 어노테이션을 사용해 컨롤러를 정의한다.
- 제품의 정보를 보관할 자료구조가 필요하므로, 레코드 클래스를 정의한다.
- 제품 정보 리스트를 보관, 수정, 삭제하는 역할을 담당하는 객체가 있어야 하므로, 해당 기능을 가진 모델 클래스를 정의한다.

## 구현할 기능
### ProductRecord
#### field
- id
- name
- price
- imageUrl
### ProductController (RestController)
- 상품 목록 GET 요청 핸들러
- 상품 POST 요청 핸들러
- 상품 정보 PUT 요청 핸들러
- 상품 DELETE 요청 핸들러
- 상품 PATCH 요청 핸들러
### ProductDAO (레포지토리)
- id 생성
- id에 해당하는 상품이 존재하는지 확인
- 상품 목록 조회
- 상품 추가
- 상품 정보 수정
- 상품 삭제
### GlobalExceptionHandler
- 자원이 존재하지 않는 경우 404 응답을 하는 핸들러