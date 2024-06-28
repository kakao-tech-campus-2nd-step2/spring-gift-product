# spring-gift-product

# 1단계
## 설명
이 상품 API는 상품과 관련된 데이터를 관리하고 제공하는 RESTful API입니다. 이를 통해 클라이언트 애플리케이션이 상품 정보를 조회하고, 추가하고, 수정하고, 삭제할 수 있습니다.


## 기능 요구사항
- 상품을 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API를 구현한다. 
- HTTP 요청과 응답은 JSON 형식으로 주고받는다. 현재는 별도의 데이터베이스가 없으므로 적절한 자바 컬렉션 프레임워크를 사용하여 메모리에 저장한다.


## 클래스 설명
1. ProductController
- RESTful API의 엔드포인트를 정의하는 컨트롤러 클래스입니다. 각 엔드포인트는 HTTP 요청을 받아 ProductService의 적절한 메서드를 호출하고, 결과를 ResponseEntity에 담아 반환합니다.
2. ProductService
- 상품 데이터를 관리하는 서비스 클래스입니다. 메모리에 데이터를 저장하고 조회, 추가, 수정, 삭제하는 메서드를 제공합니다.
3. ProductModel
- 상품의 고유 식별자(id), 이름(name), 가격(price), 이미지 URL(imageUrl)을 포함하는 데이터 모델 클래스입니다.



# 2단계 
## 설명 
이 프로젝트는 상품을 조회, 추가, 수정, 삭제할 수 있는 관리자 화면을 구현합니다. Spring Boot와 Thymeleaf를 사용하여 서버 사이드 렌더링을 기반으로 한 웹 애플리케이션을 구축합니다. 상품 이미지는 파일 업로드 대신 URL을 입력하여 관리합니다.


## 클래스 설명
add.html : 새로운 상품을 추가하는 페이지를 제공합니다. 이 페이지에는 사용자가 상품의 이름, 가격, 이미지 URL을 입력할 수 있는 폼이 포함되어 있습니다.
view.html : 특정 상품의 상세 정보를 표시하는 페이지를 제공합니다.
list.html : 모든 상품의 목록을 표시하는 페이지를 제공합니다.
edit.html : 기존 상품의 정보를 수정하는 페이지를 제공합니다.



# 3단계 
## 조건 
메모리에 저장하고 있던 모든 코드를 제거하고 H2 데이터베이스를 사용하도록 변경한다.
사용하는 테이블은 애플리케이션이 실행될 때 구축되어야 한다.


## 기능 설명
- ProductRepository.java : 데이터베이스 CRUD(Create, Read, Update, Delete) 작업을 수행하는 메서드들을 정의합니다.
- CreateTableExample.java : 데이터베이스 테이블을 생성하는 예제 코드입니다.
- DatabaseConnector.java : 데이터베이스 연결을 설정하고 관리하는 기능을 제공합니다.
- data.sql : 애플리케이션 시작 시 자동으로 실행되어 데이터베이스에 초기 데이터를 삽입합니다.
- schema.sql : 터베이스 테이블의 구조를 정의하고 생성하는 SQL 스크립트들을 포함합니다. 



## 🛠 코드 구조
```plaintext
spring-gift-product
└── src
    └── main
        ├── java
        │   └── gift
        │       ├── Application.java
        │       ├── Product.java
        │       ├── controller
        │       │   └── ProductController.java
        │       ├── model
        │       │   └── ProductModel.java
        │       ├── repository
        │       │   └── ProductRepository.java
        │       ├── service
        │       │   └── ProductService.java
        │       └── DB
        │           ├── CreateTableExample.java
        │           └── DatabaseConnector.java
        └── resources
            ├── data.sql
            └── schema.sql
            ├── static
            │   └── css
            │       └── styles.css
            └── templates
                ├── add.html
                ├── edit.html
                ├── list.html
                └── view.html
