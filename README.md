# spring-gift-product

## 1단계 요구 사항
- Product를 CRUD 하는 API를 만들자

## 객체와 기능 구현 사항
####  응답 객체 : `Response.java` ,`ListProductResponse.java`,`SingleProductResponse.java`
1. `Response.java`를 상위 클래스로 둬서, 에러메시지, 응답코드 필드를 가짐
2. `ListProductResponse.java`,`SingleProductResponse.java`는 내부 객체로 각각 `Product`와 `Product`리스트를 가짐
#### 요청 객체 : `ProductRequest.java`
1. 레코드로 뒀고, id는 받지 않는다.

#### 모델 객체 : `Product.java`
setter는 두지 않고 getter만 두었고, 초기화 생자를 이용했다.
#### 핸들러 : `ProductTransformer.java`
1. 요청객체를 모델객체로 만들어주는 코드다. 
2. 지금보니까 클래스명이 이상하다. 이후에 수정해야겠다.

#### 컨트롤러 : `ProductController.java`
1. CRUD를 구현했다.


### 새로 사용해본 것이나 느낀 점! 
- recode를 사용해봤다. <br>
일단 코드가 정말짧아져서 좋앗따.. 짧은 코드 정말 좋아요



- Responce 상위클래스를 둬서 에러메시지나 상태코드를 추가해봤다. <br>
2단계에서 API를 쓰지 않아서 무용지물이 되었지만 좋은 시도라고 생각한다! _뭐든 시도해야 뭐라도 달라지겠지?_


- 다른 분들의 코드를 열심히 분석해보았다. <br>
짧은 코드를 쓰는 것에 좀 집중하려고 했고, 팀원분이 따로 요청 객체를 둔 걸 보고 나도 따라해봤다.. ㅎ
다른 사람의 코드를 분석한 건 처음이라 즐거웠다^^ 이제 다른 분들 코드 매일 염탐해야지 ㅋ