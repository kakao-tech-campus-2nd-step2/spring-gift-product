# spring-gift-product
## 프로젝트 개요
이 프로젝트는 간단한 상품 관리 HTTP API를 구축하는 것이 목표입니다. 주요 기능으로는 상품 조회, 추가, 수정, 삭제가 있으며, 이미지를 로컬 스토리지에 저장합니다. 데이터는 메모리에 저장하고, 이미지 경로는 Base64 인코딩된 형태로 관리됩니다.

## 기능 요구 사항
상품 조회: 모든 상품 목록을 조회합니다.
상품 추가: 새로운 상품을 추가합니다.
상품 수정: 기존 상품 정보를 수정합니다.
상품 삭제: 상품을 삭제합니다.
이미지 저장: 이미지를 로컬 스토리지에 저장합니다.

## 기본 설정
언어: Java
프레임워크: Spring Framework
들여쓰기: 4 spaces
들여쓰기 깊이 제한: 최대 2
제한 사항:
3항 연산자 사용 금지
else 및 switch/case 사용 금지 , 필요한 경우 return을 사용하여 로직을 처리합니다.
lombok 사용 금지

## 구현할 기능 목록 및 Git 커밋 메시지
chore: 프로젝트 초기 설정
feat: 상품 데이터 모델 작성
feat: 로컬 스토리지 설정 및 이미지 저장 기능 구현
feat: 상품 조회 기능 구현
feat: 상품 추가 기능 구현
feat: 상품 수정 기능 구현
feat: 상품 삭제 기능 구현
feat: 입력 데이터 유효성 검사 및 예외 처리 추가
docs: README.md 파일 업데이트

## 프로젝트 구조
<img width="300" alt="image" src="https://github.com/pjhcsols/spring-gift-product/assets/110244523/35c6fc61-0a31-41fb-ba20-1d603419f536">
spring-gift-product
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── gift
│   │   │   │   │   ├── config
│   │   │   │   │   ├── controller
│   │   │   │   │   ├── model
│   │   │   │   │   ├── repository
│   │   │   │   │   ├── service
│   │   │   │   │   ├── util
│   │   │   └── ─── ─── imageStorage
│   │   └── resources
│   └── test



# 상품 관리 API

## 기능 목록
1. 프로젝트 초기 설정
   - 프로젝트 기본 구조를 생성하고, 필요한 라이브러리 및 의존성을 추가한다.

2. 데이터 모델 작성
   - 상품 데이터 모델을 생성하여 상품의 ID, 이름, 가격, 이미지 URL 등을 포함한다.

3. 상품 조회 기능 구현
   - 모든 상품을 조회할 수 있는 API를 구현한다. (GET /api/products)

4. 상품 추가 기능 구현
   - 새로운 상품을 추가할 수 있는 API를 구현한다. (POST /api/products)

5. 상품 수정 기능 구현
   - 기존 상품의 정보를 수정할 수 있는 API를 구현한다. (PUT /api/products/{id})

6. 상품 삭제 기능 구현
   - 기존 상품을 삭제할 수 있는 API를 구현한다. (DELETE /api/products/{id})

8. 이미지 저장 및 경로 관리
   - 이미지 저장 및 경로 관리 이미지는 /Users/hansol/Desktop/temp/kakao_step2_1/spring-gift-product/src/main/java/gift/imageStorage
   - 경로 암호화 처리 base64 인코딩 디코딩 해서 hashmap에 저장 후 서버내에서 디코딩해서 경로 파악 이미지 전달

9. 유효성 검사 및 예외 처리
   - 입력 데이터에 대한 유효성 검사를 수행하고, 적절한 예외 처리를 추가한다.

10. 테스트 코드 작성
   - 각 API 엔드포인트에 대한 테스트 케이스를 작성하여 기능을 검증한다.


## API 사용 방법
- **상품 조회**: `GET /api/products`
- **상품 추가**: `POST /api/products`
- **상품 수정**: `PUT /api/products/{id}`
- **상품 삭제**: `DELETE /api/products/{id}`





---
과제 1 카카오 선물하기 필수 체크 항목

핸들러 메서드의 성격에 맞게 적절한 HTTP 메서드를 사용하였는가?

---

핸들러 메서드 로직의 중복을 해결하기 위해 노력하였는가?

---

데이터베이스의 테이블을 신중하게 설계하였는가?

---

Spring의 JdbcTemplate 도구를 적절하게 사용하였는가?

---

데이터베이스 사용 로직의 중복을 해결하기 위해 노력하였는가?

---

코딩 컨벤션을 준수하고 있는가?

---

기능 단위로 나누어 적절하게 커밋하였는가?

---

Spring Framework를 공부하기 위해 학습 테스트와 같은 자신만의 흔적이 있는가?

Pull Request 제목과 내용은 아래와 같이 작성 해주세요.

**1. PR 제목:**

> PR 제목 : 부산대 BE_라이언_1주차 과제
> 

**1. PR 내용:**

> 코드 작성하면서 어려웠던 점코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분
>
