# spring-gift-product
 
Step1
# 기능 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
-HTTP 요청과 응답은 JSON 형식으로 주고받는다.
-현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

# 구현할 기능 정리
1. 컨트롤러인 ProductController 클래스 구현
2. 상품에 대한 Product 클래스 구현


-------------------------------------

Step2
# 기능 요구 사항
상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
-Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
-기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
-상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.

#구현할 기능 정리
1. templates 안에 화면에 보여질 index.html 작성
2. 화면을 통해서 상품을 조회, 추가, 수정, 삭제할 수 있도록 ProductController 수정


------------------------------------

Step3
# 기능 요구 사항
자바 컬렉션 프레임워크를 사용하여 메모리에 저장하던 상품 정보를 데이터베이스에 저장한다.

# 프로그래밍 요구 사항
메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.

# 구현할 기능 정리
1. Repository 클래스 만들기
2. 메모리와 관련된 코드들을 제거하고 데이터베이스를 사용하도록 변경하기
