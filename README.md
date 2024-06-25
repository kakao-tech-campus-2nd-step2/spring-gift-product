# spring-gift-product

To-Do list
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