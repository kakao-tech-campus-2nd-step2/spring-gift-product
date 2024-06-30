# [STEP-2] 상품 선물하기

### 과제 진행 요구 사항

- 미션은 [선물하기 상품 관리 저장소](https://github.com/kakao-tech-campus-2nd-step2/spring-gift-product)를 포크하고 클론하는 것으로 시작한다.
- [온라인 코드 리뷰 요청 1단계 문서](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step1.md)를 참고하여 실습 환경을
  구축한다.
- 기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
- Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로
  추가한다. [AngularJS Git Commit Message Convention](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)을 참고해 커밋
  메시지를 작성한다.

### 프로그래밍 요구 사항

- 자바 코드 컨벤션을 지키면서 프로그래밍 한다. (들여쓰기는 '4 spaces' 로 한다)
- indent (들여쓰기) depth 를 3이 넘지 않도록 구현한다.
- 3항 연산자를 사용하지 않는다.
- 함수는 한가지 일만 하도록 최대한 작게 만든다.
- 함수의 길이가 15 라인을 넘어가지 않도록 구현한다.
- JUnit 5 와 AssertJ 를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인한다.
- else 예약어를 사용하지 않는다.
- 도메인 로직에 단위 테스트를 구현해야 한다.(핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.)

### 기능 요구 사항 (1주차)

![이미지1](https://github.com/yunjunghun0116/cnu_db_design/assets/76200940/e819533d-8064-47d0-8357-39ace7c03650)

- [X] 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.
- [X] HTTP 요청과 응답은 JSON 형식으로 주고받는다.
- [X] 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.
- [X] Thymeleaf를 사용하여 서버 사이드 렌더링을 구현한다.
- [X] 기본적으로는 HTML 폼 전송 등을 이용한 페이지 이동을 기반으로 하지만, 자바스크립트를 이용한 비동기 작업에 관심이 있다면 이미 만든 상품 API를 이용하여 AJAX 등의 방식을 적용할 수 있다.
- [X] JdbcTemplate, SimpleJdbcinsert 및 JdbcClient와 같은 도구를 사용할 수 있다.
- [X] 상품 이미지의 경우, 파일을 업로드하지 않고 URL을 직접 입력한다.
- [X] 상품 정보에 옵션을 추가한다. 상품과 옵션 모델 간의 관계를 고려하여 설계하고 구현한다.
- [X] 하나의 상품은 여러 옵션을 가질 수 있다.
- [X] 상품을 통해 여러 옵션을 조회할 수 있다.

### 힌트

- 데이터베이스 초기화 - SQL 스크립트 (schema.sql, data.sql)

### 나만의 RULE
| HTTP Method | 사용상황                          |
|-------------|-------------------------------|
| GET         | 리소스의 조회                       |
| POST        | 새로운 리소스 생성                    |
| PUT         | 리소스의 전체 업데이트 또는 ID를 통한 리소스 생성 |
| PATCH       | 리소스의 일부분(일부 필드) 업데이트          |
| DELETE      | 리소스의 삭제                       |
