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
