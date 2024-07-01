function submitForm() {
    const form = document.getElementById('productForm');
    const formData = {
        name: form.name.value,
        price: form.price.value,
        imageUrl: form.imageUrl.value
    };

    fetch('http://localhost:8080/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })

    form.name.value = "";
    form.price.value = "";
    form.imageUrl.value = "";

    alert("Successfully added");
}