# spring-gift-product (step1)
### 클래스
* ProductController - controller
  * 상품에 대한 연산을 처리하는 핸들러가 모여 있는 클래스
  * CRUD 연산을 처리하고 결과를 반환한다.
* Product - model
  * 상품 하나하나에 대한 정보를 담는 클래스
  * Service로 분리하지 않고 로직도 포함한다.
* Products - model
  * 상품들에 모아 놓은 리스트 클래스
  * 일급 컬렉션

### ProductController 사용 방법
* CREATE - POST
  * 상품 추가: **/products/create**
* READ - GET
  * 상품 전체 조회: **/products/read**
* UPDATE - PATCH
  * id가 i인 상품 수정: **/products/i/update**
* DELETE - DELETE
  * 상품 전체 삭제: **/products/delete** 
  * id가 i인 상품 삭제: **/products/i/delete**
 
### Json Request 구조 (CREATE, UPDATE)
* id: int
* name: String
* price: int
* img: String

---
# spring-gift-product (step2)
### View
* manager.html
  * 매니저 화면
* edit-product.html
  * 매니저가 product를 수정할 수 있는 화면
* error.html
  * 예외가 발생한 경우 보여줄 화면
 
### PageController 사용 방법
* showEditPage - POST
  * edit-product.html SSR로 가져오기: **/page/edit**

### Json Request 구조 (getManagerPage)
* pw: String 필수
