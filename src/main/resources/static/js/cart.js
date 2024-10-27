document.addEventListener("DOMContentLoaded", () => {
    const cartTable = document.querySelector("tbody");
    const totalQuantityElement = document.querySelector(".cart-summary span:nth-child(2)");
    const totalPriceElement = document.querySelector(".cart-summary span:nth-child(4)");

    // 장바구니 합계 업데이트 함수
    function updateCartSummary() {
        let totalQuantity = 0;
        let totalPrice = 0;

        cartTable.querySelectorAll("tr").forEach((row) => {
            const quantity = parseInt(row.querySelector("input[type='number']").value);
            const price = parseFloat(row.querySelector("td:nth-child(3)").innerText);

            totalQuantity += quantity;
            totalPrice += quantity * price;

            row.querySelector("td:nth-child(4)").innerText = (quantity * price).toFixed(2);
        });

        totalQuantityElement.innerText = totalQuantity;
        totalPriceElement.innerText = totalPrice.toFixed(2);
    }

    // 수량 변경 시 총합 업데이트
    cartTable.addEventListener("input", (event) => {
        if (event.target.matches("input[type='number']")) {
            updateCartSummary();
        }
    });

    // 항목 삭제 시 행 삭제 및 총합 업데이트
    cartTable.addEventListener("click", (event) => {
        if (event.target.matches("button")) {
            const row = event.target.closest("tr");
            row.remove();
            updateCartSummary();
        }
    });

    // 초기 장바구니 합계 계산
    updateCartSummary();
});
