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
