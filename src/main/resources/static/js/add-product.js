// script.js

// 상품 등록 버튼 클릭 시 폼 유효성 검사
document.querySelector("form").addEventListener("submit", function (event) {
    const productName = document.getElementById("productName").value.trim();
    const productPrice = document.getElementById("productPrice").value;

    if (!productName || !productPrice) {
        alert("상품명과 가격을 입력해주세요.");
        event.preventDefault(); // 제출 방지
    }
});

// 이미지 미리보기 기능
document.getElementById("productImage").addEventListener("change", function () {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const previewImage = document.createElement("img");
            previewImage.src = e.target.result;
            previewImage.style.maxWidth = "100%";
            previewImage.style.marginTop = "10px";

            // 이전 미리보기 이미지 제거
            const previewContainer = document.getElementById("imagePreview");
            previewContainer.innerHTML = "";
            previewContainer.appendChild(previewImage);
        };
        reader.readAsDataURL(file);
    }
});
