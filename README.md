# spring-gift-product
> 카카오테크 캠퍼스 STEP2 - 1주차 클론 코딩

## 목차
[* 코드 소개](#코드-소개)<br>
[* 1단계](#-1단계-)<br>
[* 2단계](#-2단계-)<br>
[* 제약 조건](#제약-조건)<br>

## 코드 소개
카카오 선물하기의 상품을 관리하는 서비스를 구현해본다

## < 1단계 >
## [ 기능 요구 사항 ]
> 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- HTTP 요청과 응답은 JSON 형식으로 주고 받는다.
- 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
## [ 기능 목록 ]
### 상품 객체(ProductVo.java)
- 상품에 대한 정보(Id, name, price, image) 구현
- 인스턴스에 접근할 수 있는 기능
- 인스턴스를 설정할 수 있는 기능
### 상품 데이터 관리(ProductController.java)
- 상품 조회
- 상품 추가
- 상품 수정
- 상품 삭제

## < 2단계 >
## [ 기능 요구 사항 ]
> 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현한다.
- [Thymeleaf](https://www.thymeleaf.org/)를 사용하여 [서버 사이드 렌더링](https://joshua1988.github.io/vue-camp/nuxt/ssr.html)을 구현한다.
- 기본적으로는 [HTML 폼](https://developer.mozilla.org/ko/docs/Learn/Forms) 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 [AJAX](https://developer.mozilla.org/ko/docs/Glossary/AJAX) 등의 방식을 적용할 수 있다.
- 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
## [ 기능 목록 ]
### 상품 관리 화면(product-list.html)
- 상품 목록 출력
- 상품 추가
  - 버튼을 누르면 상품을 입력할 수 있도록 새로운 열(row) 생성
  - 
## 제약 조건
- [자바 코드 컨벤션](https://google.github.io/styleguide/javaguide.html)을 지키면서 프로그래밍 한다
  >예외조건: 들여쓰기는 4 space (default: 2 space)로 설정
- indent(인덴트, 들여쓰기) depth를 2이하로 구현한다
- 3항 연사자를 사용하지 않는다
- 함수(method)의 최대 라인수는 15이다
- else 예약어를 사용하지 않는다(switch/case도 사용 불가)