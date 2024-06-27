document.addEventListener('DOMContentLoaded', () => {
  window.deleteProduct = function(button) {
    const productId = button.getAttribute('data-product-id');
    if (confirm(`Do you want to delete product ID ${productId}?`)) {
      fetch(`/products/${productId}`, {
        method: 'DELETE',
      })
      .then(response => {
        if (response.ok) {
          alert('Deleted successfully.');
          location.reload(); // Reload the page to reflect the changes
        } else {
          alert('Failed to delete.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while deleting the product.');
      });
    }
  }
});

function toggleCheckboxes() {
  var checkboxes = document.getElementsByClassName('product-checkbox');
  var selectAllCheckbox = document.getElementById('selectAll');
  for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = selectAllCheckbox.checked;
  }
}

function deleteSelectedProducts() {
  var selectedProducts = [];
  var checkboxes = document.getElementsByClassName('product-checkbox');
  for (var i = 0; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) {
      selectedProducts.push(checkboxes[i].value);
    }
  }

  if (selectedProducts.length === 0) {
    alert('Please select at least one product.');
    return;
  }

  if (confirm('Do you want to delete the selected products?')) {
    deleteProducts(selectedProducts);
  }
}

function deleteProducts(productIds) {
  productIds.forEach(function(productId) {
    fetch('/products/' + productId, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
    }).then(function(response) {
      if (response.ok) {
        alert('Deleted successfully.');
        location.reload(); // Reload the page to reflect the changes
      } else {
        alert('Failed to delete.');
      }
    }).catch(function(error) {
      console.error('Error deleting product:', error);
      alert('Failed to delete product. Please try again.');
    });
  });
}
