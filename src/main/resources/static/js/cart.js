document.addEventListener("DOMContentLoaded", () => {
    const totalQuantityElement = document.querySelector(".cart-summary p:nth-child(1) span");
    const totalPriceElement = document.querySelector(".cart-summary p:nth-child(2) span");
    const cartTable = document.querySelector("table tbody");

    function updateCartSummary() {
        let totalQuantity = 0;
        let totalPrice = 0;

        // 각 제품 행을 반복하며 수량과 가격 계산
        cartTable.querySelectorAll("tr").forEach((row) => {
            const quantity = parseInt(row.querySelector("input[type='number']").value);
            const price = parseFloat(row.querySelector("td:nth-child(3)").innerText);

            // 총 수량 및 총 가격 업데이트
            totalQuantity += quantity;
            totalPrice += quantity * price;

            // 각 행의 전체 가격 업데이트 (네 번째 열에 표시)
            row.querySelector("td:nth-child(4)").innerText = (quantity * price);
        });

        // 총 수량 및 총 가격 요소에 값 설정
        totalQuantityElement.innerText = totalQuantity;
        totalPriceElement.innerText = totalPrice;
    }

    // 수량 입력 필드에 이벤트 리스너 추가하여 변경 시마다 업데이트
    cartTable.querySelectorAll("input[type='number']").forEach((input) => {
        input.addEventListener("input", updateCartSummary);
    });

    // 초기 장바구니 합계 계산
    updateCartSummary();
});
