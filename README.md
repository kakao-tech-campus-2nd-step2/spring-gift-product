# spring-gift-product


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


## 🛠 코드 구조
```plaintext
Application
 └── main(String[] args)
     └── SpringApplication.run(Application.class, args)

ProductController
 ├── 모든상품조회()
 │   └── productService.getAllProducts()
 ├── 상품조회(long id)
 │   └── productService.getProductById(id)
 ├── 상품추가(ProductModel product)
 │   └── productService.addProduct(product)
 ├── 상품수정(long id, ProductModel updatedProduct)
 │   └── productService.updateProduct(id, updatedProduct)
 └── 상품삭제(long id)
     └── productService.deleteProduct(id)

ProductService
 ├── getAllProducts()
 │   └── products.values()
 ├── getProductById(long id)
 │   └── products.get(id)
 ├── addProduct(ProductModel product)
 │   └── productModel = new ProductModel()
 │       products.put(product.id, productModel)
 ├── updateProduct(long id, ProductModel updatedProduct)
 │   └── productModel = new ProductModel()
 │       products.put(id, productModel)
 └── deleteProduct(long id)
     └── products.remove(id)

ProductModel
 ├── ProductModel()
 └── ProductModel(long id, String name, int price, String imageUrl)
