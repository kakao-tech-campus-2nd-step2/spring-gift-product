# spring-gift-product
### 클래스
* ProductController - controller
  * 상품에 대한 연산을 처리하는 핸들러가 모여 있는 클래스
  * CRUD 연산을 처리하고 결과를 반환한다.
* PageController - controller
  * manager.html에서 다른 페이지로 넘어가는 동작을 제어하는 클래스
* GlobalExceptionHandler - controller
  * 예외가 발생한 경우, 적절한 처리를 하고 에러 view를 반환하는 클래스 
* Product - model
  * 상품 하나하나에 대한 정보를 담는 클래스
  * Service로 분리하지 않고 로직도 포함한다.
* ProductDao - repository
  * DB와 Product를 사이의 operation을 제어하는 클래스
* SecurityUtility - utility
  * 관리자 화면의 비밀번호 관련 동작을 돕는 클래스
  * 비밀번호는 qwer1234입니다.

### ProductController 사용 방법
* CREATE - POST
  * 제품 추가: **/products/create**
  * manager.html에서 제품 추가 버튼을 눌러서도 사용 가능
* READ - GET (원래는 POST. 조회를 위해 일단은 GET으로 설정)
  * 제품 전체 조회: **/products/read**
  * ?password=qwer1234
* UPDATE - POST
  * id가 {id}인 제품 수정: **/products/{id}/update**
  * edit-product.html에서 정보 변경 버튼을 눌러서도 사용 가능
* DELETE - POST
  * 상품 전체 삭제: **/products/delete**
  * manager.html에서 전체 삭제 버튼을 눌러서도 사용 가능
  * id가 {id}인 상품 삭제: **/products/{id}/delete**
  * manager.html에서 제품 삭제 버튼을 눌러서도 사용 가능

### PageController 사용 방법
* showEditPage - POST
  * 제품 변경하는 페이지로 이동 : **/page/edit**
  * manager.html에서 정보 변경 버튼을 눌러서도 사용 가능
 
### Request parameter 구조
* password: String
* id: int
* name: String
* price: int
* img: String

### View
* manager.html
  * 매니저 화면
* edit-product.html
  * 매니저가 product를 수정할 수 있는 화면
* error.html
  * 예외가 발생한 경우 보여줄 화면
