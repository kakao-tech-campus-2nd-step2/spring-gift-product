# spring-gift-product
> 카카오테크 캠퍼스 STEP2 - 1주차 클론 코딩

## 목차
[* 코드 소개](#코드-소개)<br>
[* 1단계 기능 목록](#1단계-기능-목록)<br>

## 코드 소개
카카오 선물하기의 상품을 관리하는 서비스를 구현해본다

## 제약 조건
- [자바 코드 컨벤션](https://google.github.io/styleguide/javaguide.html)을 지키면서 프로그래밍 한다
  - 예외조건
  > 들여쓰기는 4 space (default: 2 space)로 설정
- indent(인덴트, 들여쓰기) depth를 2이하로 구현한다
- 3항 연사자를 사용하지 않는다
- 함수(method)의 최대 라인수는 15이다
- else 예약어를 사용하지 않는다(switch/case도 사용 불가)

## 1단계 기능 목록
### 상품 객체(ProductVo.java)
- 상품에 대한 정보(Id, name, price, image) 구현
- 인스턴스에 접근할 수 있는 기능
- 인스턴스를 설정할 수 있는 기능
### 상품 데이터 관리(ProductController.java)
- 상품 조회
- 상품 추가
- 상품 수정
- 상품 삭제