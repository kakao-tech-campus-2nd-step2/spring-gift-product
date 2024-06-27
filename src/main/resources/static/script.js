document.addEventListener('DOMContentLoaded', function () {
    const productList = document.getElementById('product-list');
    const apiEndpoint = 'http://localhost:8080/api/products';

    // 상품 목록 조회
    function fetchProducts() {
        axios.get(apiEndpoint)
        .then(response => {
            const products = response.data;
            productList.innerHTML = '';
            products.forEach(product => {
                const productItem = document.createElement('div');
                productItem.className = 'product-item';
                productItem.innerHTML = `
                        <img src="${product.imageUrl}" alt="${product.name}">
                        <span>${product.id}</span>
                        <span>${product.name}</span>
                        <span>${product.price}원</span>
                        <button onclick="editProduct(${product.id})">수정</button>
                        <button onclick="deleteProduct(${product.id})">삭제</button>
                    `;
                productList.appendChild(productItem);
            });
        })
        .catch(error => console.error('상품 목록 조회 에러: ', error));
    }

    // 초기 상품 목록 조회
    fetchProducts();
});
