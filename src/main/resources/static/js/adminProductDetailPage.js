const h1 = document.querySelector("h1");

function deleteProduct() {
    fetch(`http://localhost:8080/api/products/${h1.id}`, {
        method: 'DELETE',
    })
    window.location.href = "http://localhost:8080/api/products/admin/deleted";
}

function submitForm() {
    const form = document.getElementById('productForm');
    const formData = {
        name: form.name.value,
        price: form.price.value,
        imageUrl: form.imageUrl.value
    };

    fetch(`http://localhost:8080/api/products/${h1.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })

    alert("Successfully edited");
}