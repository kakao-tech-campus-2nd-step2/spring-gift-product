# spring-gift-product
1. Menu DTO 생성
2. Menu create 
3. Menu delete
4. Menu search
5. Menu update

<h>Step2</h>
<p>
Menu.html : 첫 화면을 보여준다. 메뉴를 추가하는 부분이 있다.
update_menu.html : 수정 화면을 보여준다. 이전에 저장했던 값이 들어간다.

MenuController에서 GET, POST로만 받고, 이를 처리하는 과정을 구현했다.

Step3
<p></p>
Jdbc탬플릿으로 직접 h2데이터베이스에 값을 넣고, 이를 조회하는 과정을 수행한다.
jdbc를 데이터베이스와 연동
MenuRepository에 있던 해시맵을 지우고, 데이터베이스와 연동하도록 설계