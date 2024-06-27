# spring-gift-product

step-1의 구현 사항
0. 데이터베이스 : 별도의 데이터베이스가 없으므로 컬렉션인 HashMap 사용
1. 상품을 추가, 삭제, 수정, 조회할 수 있는 HTTP API 구현
   1. 추가 가능 : Post에 해당하며, Product를 입력받아 HashMap에 저장한다.
   2. 삭제 기능 : Delete에 해당하며, key값을 입력받아 HashMap에서 제거한다.
   3. 수정 기능 : Put에 해당하며, key값과 Product를 입력받아 HashMap에 업데이트한다.
   4. 조회 기능 : Get에 해당하며, HashMap에 있는 모든 데이터를 불러오거나, 특정 key값에 해당하는 데이터를 불러온다.

step-2의 구현 사항
1. thymeleaf를 이용한 관리자 페이지 구현
   1. 조회 시 사용할 products.html의 구현(id, name, price, imageUrl을 컨트롤러에서 받아 테이블 형태로 화면에 출력)
   2. products.html에 사용할 css파일(style.css) 구현
   3. 조회 기능을 담당하는 메소드에서 product.html을 사용하도록 연결
2. step-1에서 미흡한 사항 보완
   1. json형태로 통신하는 것을 명시하기 위해 추가, 삭제, 수정을 담당하는 메소드에 @RequestBody 어노테이션 추가
   2. 추가, 삭제, 수정 시 완료 여부를 String 값으로 리턴 => ResponseEntity형태로 변경, Status와 Body를 추가하여 리턴하며, 기존의 String 내용은 Body에 담음.