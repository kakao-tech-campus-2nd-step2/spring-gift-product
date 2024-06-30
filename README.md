# spring-gift-product

## step 3

- H2 데이터베이스 연결
  - data.sql : 스키마 정의하는 파일 --> 테이블 만들기
  - schema.sql : 데이터베이스에 삽입할 초기 데이터 정의하는 파일
  - application.properties 수정 --> 애플리케이션 시작 시점에 schema.sql과 data.sql 파일 실행

- HashMap에서 Dao로 변경
  - ProductDao 파일 생성
    - jdbcTemplate 사용하여 crud 구현
  - ProductService 파일 수정