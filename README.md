# spring-gift-product

## 상품을 조회, 추가, 삭제할 수 있는 간단한 HTTP API 구현하기
    HTTP 요청 및 응답: JSON 형태
    자바 컬렉션 프레임워크 활용 메모리에 저장
    상품 조회
        - Request: GET
        - ex) GET /api/products HTTP/1.1
        - Response
        - ex)  HTTP/1.1 200
          Content-Type: application/json
          [
          {
          "id": 8146027,
          "name": "아이스 카페 아메리카노 T",
          "price": 4500,
          "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
          }
          ]
        - 상품 추가
            - POST
                - Request 예시
                    POST /api/products HTTP/1.1
                    Content-Type: application/json
                    
                    {
                      "name": "아이스 카페 라떼 T",
                      "price": 5000,
                      "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_b7d4c9d1c5e34b929f8e13fa6c2a9f1e.jpg"
                    }

                - Response 예시
                    HTTP/1.1 201 Created
                    Content-Type: application/json
                    
                    {
                      "id": 8146028,
                      "name": "아이스 카페 라떼 T",
                      "price": 5000,
                      "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_b7d4c9d1c5e34b929f8e13fa6c2a9f1e.jpg"
                    }
    상품 삭제
            - DELETE
                - Request 예시

                    DELETE /api/products/8146027 HTTP/1.1

                - Response 예시

                    HTTP/1.1 204 No Content

    상품 수정
            - PUT
                - Request 예시

                    PUT /api/products/8146027 HTTP/1.1
                    Content-Type: application/json
                    
                    {
                      "name": "아이스 카페 아메리카노 T",
                      "price": 4700,
                      "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
                    }
                    ```

                - Response예시

                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    
                    {
                      "id": 8146027,
                      "name": "아이스 카페 아메리카노 T",
                      "price": 4700,
                      "imageUrl": "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
                    }
