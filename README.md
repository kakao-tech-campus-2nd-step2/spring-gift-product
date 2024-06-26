# spring-gift-product

To-Do list
1.
Application 클래스
 - springApplication 실행 기능
Product 클래스
 - product 객체 생성 및 멤버 반환, 수정 기능
 - 멤버 변수는 long id, String name, int price, String imageUrl
ProductController 클래스
 - hashmap을 사용해 product 객체를 저장하고, 객체에 대한 조회, 추가, 수정, 삭제 기능
 - GET:id를 사용해 제품을 검색한 후 반환하는 조회 기능
 - POST:새로운 상춤을 추가하는 추가 기능
 - PUT:해당 id의 제품 변수를 수정하는 수정 기능
 - DELETE:해당 id의 제품을 해시맵에서 삭제하는 삭제 기능
2.
Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
HTML 폼 전송 등을 이용한 페이지 이동방식을 사용한다.

ProductController의 각 조회, 추가, 수정, 삭제 기능을 담당하는 메서드들과 Products.html(메인화면)을 thymeleaf로 연결시킴.

Product.html
메인화면을 구성하며 제품들의 세부정보를 볼 수 있다.(조회)
수정,삭제,추가 버튼을 눌러 해당 기능을 수행할 수 있다.

Add_product.html
추가 기능을 수행할 때 보여지는 화면을 구성하는 html.

Edit_product.html
수정 기능을 수행할 때 보여지는 화면을 구성하는 html.

+삭제 기능은 사용자에게 추가 입력을 요구하지 않으므로 메인 화면에서 즉시 실행가능.

Add_Edit_product.css
Add_product.html와 Edit_product.html의 style을 구성하는 파일.
보여지는 모든 요소를 중간, 세로 정렬시킴.

Products.css
Products.html의 style을 구성하는 파일.
테이블에 선과 간격을 설정하고 수정, 삭제, 추가 기능 버튼에 대해 스타일을 설정함.