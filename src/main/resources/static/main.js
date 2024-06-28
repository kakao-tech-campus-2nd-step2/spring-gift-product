document.getElementById('product-add-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const productData = {
        name: document.getElementById('name').value,
        price: document.getElementById('price').value,
        imageUrl: document.getElementById('imageUrl').value
    };

    fetch('/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/admin/products';
        } else {
            return response.json().then(errorData => {
                throw new Error(errorData.message || '상품을 등록하지 못하였습니다.');
            });
        }
    })
    .catch(error => {
        alert('Error: ' + error.message);
    });
});