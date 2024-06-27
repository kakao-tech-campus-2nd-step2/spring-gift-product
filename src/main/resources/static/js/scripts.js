function createProducts() {
    const product = {
        id: document.getElementById('productId').value,
        name: document.getElementById('productName').value,
        price: document.getElementById('productPrice').value,
        imageUrl: document.getElementById('productImageUrl').value
    };

    fetch('/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    }).then(response => {
        if (response.status == 201) {
            alert("상품이 생성되었습니다.");
            loadProducts();
        } else {
            alert("상품이 생성되지 않았습니다.");
        }
    });
}

function loadProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(products => {
            const productsTableBody = document.getElementById("productsTableBody");
            productsTableBody.innerHTML = '';
            products.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${product.id}</td>
                        <td><span>${product.name}</span><input type="text" value="${product.name}" style="display:none"></td>
                        <td><span>${product.price}</span><input type="text" value="${product.price}" style="display:none"></td>
                        <td><span>${product.imageUrl}</span><input type="text" value="${product.imageUrl}" style="display:none"></td>
                        <td>
                            <button class="btn btn-warning" onclick="editProduct(${product.id}, this)">수정 버튼</button>
                            <button class="btn btn-primary" onclick="saveProduct(${product.id}, this)" style="display:none">저장 버튼</button>
                            <button class="btn btn-danger" onclick="deleteProduct(${product.id})">삭제 버튼</button>
                        </td>
                    `;
                productsTableBody.appendChild(row);
            });
        });
}