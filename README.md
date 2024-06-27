
## Step1 구현 기능
- 상품 조회
  - 상품 전체 조회 URL : http://localhost:8080/api/products
  - 단일 상품 조회 URL : http://localhost:8080/api/products/idNumber
- 상품 추가
  - 터미널 curl기능으로 테스트 가능
  - 예시1) curl.exe -X POST -H "Content-Type: application/json" -d '{\"id\":1, \"name\":\"Product1\"}' http://localhost:8080/api/products
  - 예시2) curl.exe -X POST -H "Content-Type: application/json" -d '{\"id\":2, \"name\":\"Product2\"}' http://localhost:8080/api/products
  - 예시3) curl.exe -X POST -H "Content-Type: application/json" -d '{\"id\":3, \"name\":\"Product3\"}' http://localhost:8080/api/products
- 상품 수정
  - 터미널 curl기능으로 테스트 가능
  - 예시) curl.exe -X PUT -H "Content-Type: application/json" -d '{\"id\":2, \"name\":\"Updated Product2\"}' http://localhost:8080/api/products/2
- 상품 삭제
  - 터미널 curl기능으로 테스트 가능
  - 예시) curl.exe -X DELETE http://localhost:8080/api/products/1

---

## Step2 관리자 화면 구성
- Step1의 문제점 
  - 클라이언트가 URL로 접근 하였을 때, json형식의 상품 정보만 나온다.
  - 상품 조회는 URL로 가능하지만 추가,수정 및 삭제는 test를 통해서밖에 못한다.
- Step2는 관리자 화면을 구성하여, 삼품을 CURD할 수 있게 한다.

## Step3 데이터베이스 적용
- Step1의 문제점
  - 데이터 베이스가 따로 없어서 컬렉션 프레임워크를 사용하여 메모리에 상품 정보를 저장했다.
  - 그래서 서버를 종료시키면 초기화되는 문제가 발생했다.
- Step3에서는 H2 데이터베이스를 사용하여 상품 정보를 저장하도록 수정한다.
  - 기존의 데이터베이스로 사용하던 컬렉션 프레임워크 사용을 제거한다.