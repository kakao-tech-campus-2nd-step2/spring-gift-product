# spring-gift-product

# 1. Product.java
가장 기본적인 model class<br>
constructor , setter , getter 메서드로 구성되었습니다.

# 2. ProductController.java

@GetMapping <br>
http로부터 정보를 입력받음

@GetMapping("/{id}") <br>
이렇게하면 상품을 조회할수있음

@PostMapping<br>
상품을 추가함

@DeleteMapping("/{id}")<br>
특정 id를 입력받아서 있으면 삭제시킴

@PutMapping("/{id}") <br>
id를 입력받아서 수정시킴

# 3. 난관
1 )    처음에는 postman을 사용하지 않고 web을 이용해서 결과값을
확인했는데 , 그렇게 하다보니 나중에 상품조회라던가 , 상품삭제시에 문제가
발생했습니다. 그래서 코드수정을 하고 postman을 사용했더니 해결되었습니다.
<br>
2 )  pr을 잘못 날려서 일단 closed 하고 pr을 삭제하려고 했는데 권한이 없어 삭제
할수 없었습니다. 그래서 검색해보니 branch가 동일하다면 commit시에 
자동으로 pr에 수정된다고 해서 reopend 했더니 정말로 적용이 되었습니다.
