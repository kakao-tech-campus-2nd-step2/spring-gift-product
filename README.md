# spring-gift-product
# 1단계 요구 사항
1. 상품 정보 조회
   - 상품 정보 전체 조회
   - 상품 정보 id에 따른 조회
2. 상품 정보 추가
3. 상품 정보 수정
4. 상품 정보 삭제
   - 상품 id에 따라 삭제 기능

# 2단계 요구사항
- 상품 정보를 조회, 추가, 수정, 삭제할 수 있는 관리자 페이지 생성
   - productMemroyRepository를 이용해서 메모리에 저장하는 형식으로 구현
   - HTML, CSS 이용하여 SSR 구현

# 3단계 요구사항
- ProductMemoryRepository를 이용해서 메모리에 저장하던 방식을 H2 데이터베이스 이용하는 방식으로 변경
- ProductH2Repository 구현
  - 그에 따른 service, controller 변경