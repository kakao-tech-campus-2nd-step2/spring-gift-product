# spring-gift-product
### 기능 요구 사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
- Thymeleaf를 사용하여 서버 사이드 랜더링을 구현한다.
- 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 한다.
- (추가) 비동기 작업에 관심이 있다면, 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- 상품의 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

### 구현할 기능 목록
ProductRepository
- [ ] DB연결 대신 HashMap으로 대체 (단계 1에서 구현 완료)

ProductService
- [ ] 상품 CRUD Service (단계 1에서 구현 완료)

ProductController
- [ ] listProducts: 상품 목록 조회 및 랜더링
- [ ] showAddProductForm: 상품 추가 폼 랜더링
- [ ] addProduct: 추가한 상품 정보 저장
- [ ] showEditProductForm: 특정 상품을 수정하기 위한 입력 폼 랜더링
- [ ] updateProduct: 폼에서 수정된 상품 정보를 저장하는 기능
- [ ] deleteProduct: 특정 상품을 삭제

웹페이지
- [ ] 상품 목록 메인 페이지
- [ ] 상품 추가 페이지
- [ ] 상품 수정 페이지

꾸미기
- [ ] 부트 스트랩 적용