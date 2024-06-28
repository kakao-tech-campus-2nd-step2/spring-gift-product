$(document).ready(function() {
    loadProducts();
});

function loadProducts() {
    $.ajax({
        url: '/api/products',
        method: 'GET',
        success: function(data) {
            var productTable = $('#productTable');
            productTable.empty();
            data.forEach(function(product) {
                productTable.append('<tr>'
                    + '<td>' + product.name + '</td>'
                    + '<td>' + product.price + '</td>'
                    + '<td>' + product.imageUrl + '</td>'
                    + '<td><button onclick="editProduct(' + product.id + ')">Edit</button>'
                    + '<button onclick="deleteProduct(' + product.id + ')">Delete</button></td>'
                    + '</tr>');
            });
        }
    });
}

function openAddProductModal() {
    document.getElementById('addProductModal').style.display = 'block';
}

function closeAddProductModal() {
    document.getElementById('addProductModal').style.display = 'none';
}

function addProduct() {
    var formData = {
        name: $('#name').val(),
        price: $('#price').val(),
        imageUrl: $('#imageUrl').val()
    };

    $.ajax({
        url: '/api/products',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function() {
            closeAddProductModal();
            loadProducts();
        },
        error: function(xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}

function editProduct(id) {
    $.ajax({
        url: '/api/products/' + id,
        method: 'GET',
        success: function(product) {
            $('#editId').val(product.id);
            $('#editName').val(product.name);
            $('#editPrice').val(product.price);
            $('#editImageUrl').val(product.imageUrl);
            document.getElementById('editProductModal').style.display = 'block';
        },
        error: function(xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}

function closeEditProductModal() {
    document.getElementById('editProductModal').style.display = 'none';
}

function updateProduct() {
    var formData = {
        id: $('#editId').val(),
        name: $('#editName').val(),
        price: $('#editPrice').val(),
        imageUrl: $('#editImageUrl').val()
    };

    $.ajax({
        url: '/api/products/' + formData.id,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function() {
            closeEditProductModal();
            loadProducts();
        },
        error: function(xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}

function deleteProduct(id) {
    $.ajax({
        url: '/api/products/' + id,
        method: 'DELETE',
        success: function() {
            loadProducts();
        },
        error: function(xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}
