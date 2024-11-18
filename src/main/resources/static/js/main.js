// 기본 인터랙션을 위한 JS 예제
document.addEventListener("DOMContentLoaded", () => {
    console.log("쇼핑몰 메인 페이지가 로드되었습니다!");
});

function changeImage(imageSrc) {
    document.getElementById('main-image').src = imageSrc;
}