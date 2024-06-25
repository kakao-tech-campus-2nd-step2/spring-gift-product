# spring-gift-product

## 과제 진행 요구 사항

미션은 선물하기 상품 관리 저장소를 포크하고 클론하는 것으로 시작한다.
온라인 코드 리뷰 요청 1단계 문서를 참고하여 실습 환경을 구축한다.
미션 시작 버튼을 클릭하여 미션을 시작한다.
저장소에 GitHub 사용자 이름으로 브랜치가 생성되었는지 확인한다.
저장소를 내 계정으로 포크한다.
기능을 구현하기 전 README.md에 구현할 기능 목록을 정리해 추가한다.
Git의 커밋 단위는 앞 단계에서 README.md에 정리한 기능 목록 단위로 추가한다.
AngularJS Git Commit Message Conventions을 참고해 커밋 메시지를 작성한다.

## 기능 요구 사항

상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다.

HTTP 요청과 응답은 JSON 형식으로 주고받는다.
현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.

## 시나리오

상품을 먼저 생성(추가)하는 기능 만든 후 조회, 수정, 삭제의 순서로 개발을 한다. DB는 따로 사용하지 않고 자바 컬렉션 프레임 워크를 사용한다.

1. 상품을 먼저 구현한다.(레코드로 구현)
    1. 상품은 id(Long), name(String), price(int), imageUrl(String)을 가진다.
    2. Map<id, product>와 product의 id는 다른것 같음 그러면 이 ID는 뭐라고 하지?
1. 상품을 추가하는 HTTP API 구현
    1. 상품을 추가하는 컨트롤러를 구현한다.
    2. Jackson 등을 통해 JSON 형식으로 응답이 되도록 한다.
1. 상품을 조회하는 HTTP API를 구현한다.
    1. 상품을 추가하는 @GetMapping 구현한다.
    2. Jackson 등을 통해 JSON 형식으로 응답이 되도록 한다.

## 프로그래밍 요구 사항

자바 코드 컨벤션을 지키면서 프로그래밍한다.
기본적으로 Google Java Style Guide를 원칙으로 한다.
단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
3항 연산자를 쓰지 않는다.
함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
else 예약어를 쓰지 않는다.
else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.

### 참고

Oracle JDK vs Amazon Corretto JDK
먼저 Oracle JDK와 Open JDK라는 기준으로 분류할 수 있다.

Oracle JDK: 오라클사에서 제공하는 JDK, 구독을 통해 유료 라이센스를 구매할 수 있다.

Open JDK: 유명한 무료 JDK이며 OpenJDK 기반으로 빌드된 JDK사용이 추천된다.
Open JDK에는 Azul Zulu, Amazon Corretto, Temurin 등이 있다.

Amazon Corretto: 무료 멀티플랫폼이며 보안 수정 및 성능 개선을 포함하는 장기 지원이 제공된다. 최근에는 Oracle사보다 훨씬 더 많은 점유율을 보여주고 있다.