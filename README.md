# spring-gift-product

## step1 - 상품 API
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API 구현

### model
	- 필드 선언, 생성자 초기화
	- 상품의 id, name, price, imageUrl에 대한 setter/getter 생성

### controller
	- CRUD 기능에 대한 API 엔드포인트
		- POST api/products : 새로운 상품 생성
		- GET api/products/{id} : 특정 상품 조회
            - GET api/products : 전체 상품 조회 
		- PUT api/products/{id} : 특정 상품 정보 업데이트
		- DELETE api/products/{id} : 특정 상품 삭제
		
	- 각 기능에 대한 예외처리를 위해 Service 호출 후 그 결과를 JSON 형식으로 반환
		-JSON 응답 형식
		  ```json
		  {    
		    "id": 8146027,
		    "name": "아이스 카페 아메리카노 T",
		    "price": 4500,
		    "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
		  }
             '''

### Service
	- CRUD 작업에 대한 예외 처리
		- C(Create) : 기존에 없는 상품인지 확인
		- R(Read) : 가지고 있는 목록 중 존재하는 상품인지 확인
            - U(Update) : 가지고 있는 목록 중 존재하는 상품인지 확인
		- D(Delete) : 삭제할 상품이 존재하는 지 확인
		
	- 결과를 controller로 반환

### HTTP 상태 코드
- 적절한 HTTP 상태 코드를 사용한다.

---
## step2 - 관리자 화면
- 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면 구현
- 기존의 JSON 형식으로 반환하던 상품 정보를 관리자 화면을 통해 관리할 수 있도록 개선
- Thymeleaf를 사용해 서버 사이드 렌더링을 구현 
- 
---
## step3 - DB 연결
- repository에서 Spring JDBC를 사용하여 DB를 활용하는 클래스를 생성
    - JDBCTemplate 초기화
    - 각각의 메서드가 메모리상의 데이터가 아닌 실제 DB에서 CRUD 작업을 처리할 수 있도록 코드 수정