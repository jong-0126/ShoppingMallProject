//
document.addEventListener("DOMContentLoaded", function() {
    const buttons = document.querySelectorAll(".buy-btn");
    buttons.forEach(button => {
        button.addEventListener("click", function() {
            const itemKey = this.getAttribute("data-item-key");
            // URL을 itemDetail?item_key={itemKey} 형식으로 수정
            window.location.href = `/itemDetail?item_key=${itemKey}`;
        });
    });
});