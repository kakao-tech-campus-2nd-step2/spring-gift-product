# spring-gift-product

### 작업 배경

- 현재까지 아직은 사용할 DB가 선정되지 않은 상태임
- 기존에는 hashmap으로 DB를 대체하여 구현하였음
- 해당 repository 로직을 h2 메모리 기반 DB 로직으로 리팩토링하여, 개발 단계에서의 임시적인 DB를 활용하도록 하고자 함.

<br/>
<br/>

### 구현 범위

1. 어플리케이션 실행 시 Product 테이블을 생성하도록 스키마 파일 작성

<br/>

2. repository의 save 메소드 리팩토링
3. repository의 findAll 메소드 리팩토링
4. repository의 findById 메소드 리팩토링
5. repository의 update 메소드 리팩토링
6. repository의 delete 메소드 리팩토링

<br/>

7. service, jdbcTemplate을 빈으로 등록하고, 의존성 관리 로직 추가

<br/>
<br/>

### 작업 방식

1. repository의 모든 메소드에 대하여, 해당 로직들 모두 jdbcTemplate을 사용하여 h2 DB에 CRUD를 수행하도록 리팩토링. 기존의 hashmap 필드는 제거하였음.

<br/>

2. 의존성 관리 로직 추가 : 생성자 주입 방식 채택. 이에 따라 적절한 어노테이션과 생성자를 추가하였음.

<br/>

3. jdbcTemplate에 생성자 주입 방식을 적용하였으므로, 기존에 직접 datasource를 정의하고 이를 기반으로 jdbcTemplate을 제공하던 config.java 파일을 삭제하였고, 컨테이너가 jdbcTemplate을 주입해줄 수 있도록 알맞은 생성자와 Autowired 어노테이션을 명시하였음.

<br/>

4. ProductService 클래스에 Service 어노테이션을 명시하여 빈으로 등록하였고, 이를 통해 ProductController의 서비스 필드에 생성자 기반 의존성 주입이 가능하도록 하였음.