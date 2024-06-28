const newProductModal = document.querySelector('.new_product');
const newProductModalOpen = document.querySelector('.new_product_modal_btn');
const newProductModalClose = document.querySelector('.new_product .close_btn');

//열기 버튼을 눌렀을 때 모달팝업이 열림
newProductModalOpen.addEventListener('click',function(){
  //'on' class 추가
  newProductModal.classList.add('on');
});
//닫기 버튼을 눌렀을 때 모달팝업이 닫힘
newProductModalClose.addEventListener('click',function(){
  //'on' class 제거
  newProductModal.classList.remove('on');
});
document.getElementById('new_product_form').addEventListener('submit',
    function (event) {
      event.preventDefault();

      const formData = new FormData(event.target);
      const data = {};

      formData.forEach((value, key) => {
        data[key] = value;
      });

      // JSON 형식으로 변환
      const jsonData = JSON.stringify(data);

      // fetch를 이용하여 서버로 전송
      fetch('/api/products', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: jsonData
      })
      .then(response => response.json())
      .then(data => {
        // newProductModal.classList.remove('on');
        location.reload()
      })
    });

