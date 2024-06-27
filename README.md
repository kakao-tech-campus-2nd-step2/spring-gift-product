# spring-gift-product

# 1. ProductWebController
원래 처음에는 ProductController를 수정해서 step2를 실행하려고 했는데
기존 코드는 그대로 가져가야 나중에 merge 시에 충돌이 없을것 같아서
productWebController를 새로 생성했습니다.

# 2. 그외
그외의 코드는 step1 과 동일 합니다.

# 3. html
1. productList - 메인 화면 이고 모든 데이터에 대한 정보가 테이블로 표시됩니다.
2. productDetail- 메인화면에서 view버튼을 누르면 세부사항이 화면에 표시됩니다.
3. addProduct- 상품추가를 할수 있는 빈칸과 버튼을 만들어 내는 화면이 표시됩니다.
4. editProduct- product 의 모두 요소를 수정할수 있습니다.
5. 404 - 오류가 나타낼때 화면에 출력할 화면을 표시합니다.

