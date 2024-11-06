// 예시로 총 상품 수량 및 총 가격을 설정합니다
const itemCount = 3; // 실제 값으로 설정
const totalPrice = 150000; // 실제 값으로 설정

document.getElementById("itemCount").textContent = itemCount;
document.getElementById("totalPrice").textContent = totalPrice;

function submitOrder() {
    const address = document.getElementById("address").value;

    if (!address) {
        alert("주소를 선택해 주세요.");
        return;
    }

    const orderData = {
        address,
        itemCount,
        totalPrice
    };

    console.log("주문 데이터:", orderData);
    alert("주문이 완료되었습니다!");
}
